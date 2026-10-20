import java.util.*;

class Course {
    private final String courseCode;
    private final String title;
    private final int capacity;
    private final Set<String> enrolledStudentIds; // Fast lookups, uniqueness
    private final List<String> prerequisites;     // Ordered prerequisites
    private final Deque<String> waitlist;          // FIFO queue via LinkedList

    public Course(String courseCode, String title, int capacity) {
        this.courseCode = courseCode;
        this.title = title;
        this.capacity = capacity;
        this.enrolledStudentIds = new HashSet<>();
        this.prerequisites = new ArrayList<>();
        this.waitlist = new LinkedList<>();
    }

    public void addPrerequisite(String prereqCode) {
        prerequisites.add(prereqCode);
    }

    public String getCourseCode() { return courseCode; }
    public String getTitle() { return title; }
    public int getCapacity() { return capacity; }
    public Set<String> getEnrolledStudentIds() { return enrolledStudentIds; }
    public List<String> getPrerequisites() { return prerequisites; }
    public Deque<String> getWaitlist() { return waitlist; }

    public boolean isFull() {
        return enrolledStudentIds.size() >= capacity;
    }

    public boolean enroll(String studentId) {
        if (!isFull()) {
            return enrolledStudentIds.add(studentId);
        }
        return false;
    }

    public boolean addToWaitlist(String studentId) {
        if (!waitlist.contains(studentId) && !enrolledStudentIds.contains(studentId)) {
            return waitlist.offer(studentId);
        }
        return false;
    }

    /**
     * Drops a student from enrollment and automatically promotes 
     * the next eligible candidate from the waitlist.
     */
    public String drop(String studentId) {
        boolean removed = enrolledStudentIds.remove(studentId);
        if (!removed) {
            waitlist.remove(studentId);
            return null; // Student was not enrolled
        }

        // Automatic waitlist promotion
        if (!waitlist.isEmpty()) {
            String promotedStudent = waitlist.poll();
            enrolledStudentIds.add(promotedStudent);
            return promotedStudent;
        }
        return null;
    }
}

class RegistrationPortal {
    private final Map<String, Course> courses = new HashMap<>();
    // Tracks completed courses per student for prerequisite validation
    private final Map<String, Set<String>> studentCompletedCourses = new HashMap<>();

    public void addCourse(Course course) {
        courses.put(course.getCourseCode(), course);
    }

    public void recordCompletedCourse(String studentId, String courseCode) {
        studentCompletedCourses.computeIfAbsent(studentId, k -> new HashSet<>()).add(courseCode);
    }

    public String registerStudent(String studentId, String courseCode) {
        Course course = courses.get(courseCode);
        if (course == null) {
            return "Registration Failed: Course " + courseCode + " does not exist.";
        }

        if (course.getEnrolledStudentIds().contains(studentId)) {
            return "Already Enrolled: Student " + studentId + " is already in " + courseCode + ".";
        }

        // 1. Check prerequisites
        Set<String> completed = studentCompletedCourses.getOrDefault(studentId, Collections.emptySet());
        for (String prereq : course.getPrerequisites()) {
            if (!completed.contains(prereq)) {
                return "Prerequisite Missing: Student lacks required course " + prereq + ".";
            }
        }

        // 2. Attempt enrollment or waitlist placement
        if (!course.isFull()) {
            course.enroll(studentId);
            return "Enrolled: Student " + studentId + " successfully registered for " + courseCode + ".";
        } else {
            boolean waitlisted = course.addToWaitlist(studentId);
            if (waitlisted) {
                return "Course Full: Student " + studentId + " placed on waitlist at position #" 
                        + course.getWaitlist().size() + ".";
            } else {
                return "Waitlist Redundant: Student is already queued.";
            }
        }
    }

    public String dropStudent(String studentId, String courseCode) {
        Course course = courses.get(courseCode);
        if (course == null) return "Course not found.";

        String promoted = course.drop(studentId);
        if (promoted != null) {
            return "Student " + studentId + " dropped. Automatically promoted " + promoted + " from waitlist.";
        }
        return "Student " + studentId + " dropped. No waitlist candidates available.";
    }
}
