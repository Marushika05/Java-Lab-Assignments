import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Result<T extends Number> {
    private final String studentName;
    private final List<T> marks;
    private final double maxMarksPerSubject;

    // 5. Objects.requireNonNull() validates non-null parameters
    public Result(String studentName, List<T> marks, double maxMarksPerSubject) {
        this.studentName = Objects.requireNonNull(studentName, "Student name cannot be null");
        this.marks = Objects.requireNonNull(marks, "Marks list cannot be null");
        this.maxMarksPerSubject = maxMarksPerSubject;
    }

    // 1 & 2. Calculate percentage using doubleValue()
    public double calculatePercentage() {
        if (marks.isEmpty()) {
            return 0.0;
        }
        double total = 0.0;
        for (T mark : marks) {
            total += Objects.requireNonNull(mark, "Mark entry cannot be null").doubleValue();
        }
        return (total / (marks.size() * maxMarksPerSubject)) * 100.0;
    }

    // 3. Determine pass/fail based on a threshold percentage
    public boolean hasPassed(double thresholdPercentage) {
        return calculatePercentage() >= thresholdPercentage;
    }

    // 4. Display result using Optional if marks are available
    public void displayResult(double passingThreshold) {
        Optional.ofNullable(marks.isEmpty() ? null : marks)
                .ifPresentOrElse(
                        m -> {
                            double percentage = calculatePercentage();
                            boolean passed = hasPassed(passingThreshold);
                            System.out.printf("Student: %s | Percentage: %.2f%% | Status: %s%n",
                                    studentName, percentage, (passed ? "PASSED" : "FAILED"));
                        },
                        () -> System.out.println("No marks available for student: " + studentName)
                );
    }

    public static void main(String[] args) {
        List<Integer> intMarks = List.of(85, 78, 92, 65, 88);
        Result<Integer> r1 = new Result<>("Aarav", intMarks, 100.0);
        r1.displayResult(40.0);

        List<Double> doubleMarks = List.of(35.5, 38.0, 42.0);
        Result<Double> r2 = new Result<>("Diya", doubleMarks, 100.0);
        r2.displayResult(40.0);

        Result<Integer> rEmpty = new Result<>("Kabir", List.of(), 100.0);
        rEmpty.displayResult(40.0);
    }
}
