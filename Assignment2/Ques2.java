// Q2: Smart Library Resource Management System

// Abstract class
abstract class LibraryResource {

    // Private variables
    private int resourceId;
    private String title;
    private String author;

    // Static variable
    private static String libraryName = "Smart Central Library";

    // Static object counter
    private static int totalResources = 0;

    // Constructor
    public LibraryResource(int resourceId, String title, String author) {
        this.resourceId = resourceId;
        this.title = title;
        this.author = author;

        totalResources++;
    }

    // Getters
    public int getResourceId() {
        return resourceId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    // Setters
    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    // Static getter
    public static String getLibraryName() {
        return libraryName;
    }

    // Static setter
    public static void setLibraryName(String libraryName) {
        LibraryResource.libraryName = libraryName;
    }

    // Protected method
    protected void displayBasicDetails() {
        System.out.println("Resource ID : " + resourceId);
        System.out.println("Title       : " + title);
        System.out.println("Author      : " + author);
    }

    // Abstract method
    public abstract double calculateFine(int overdueDays);

    // Static method
    public static void displayTotalResources() {
        System.out.println(
            "Total Resources Created: " + totalResources
        );
    }
}


// Interface
interface Printable {

    void printDetails();
}


// Book class
class Book extends LibraryResource implements Printable {

    public Book(int resourceId, String title, String author) {
        super(resourceId, title, author);
    }

    // Fine = Rs. 5 per overdue day
    @Override
    public double calculateFine(int overdueDays) {

        if (overdueDays < 0) {
            return 0;
        }

        return overdueDays * 5;
    }

    // Implementing Printable
    @Override
    public void printDetails() {

        System.out.println("----------------------------------------");
        System.out.println("Resource Type: Book");

        // Calling protected method
        displayBasicDetails();

        System.out.println("Fine Rate    : Rs. 5/day");
        System.out.println("----------------------------------------");
    }
}


// DigitalResource class
class DigitalResource extends LibraryResource implements Printable {

    public DigitalResource(int resourceId, String title, String author) {
        super(resourceId, title, author);
    }

    // Fine = Rs. 2 per overdue day
    @Override
    public double calculateFine(int overdueDays) {

        if (overdueDays < 0) {
            return 0;
        }

        return overdueDays * 2;
    }

    // Implementing Printable
    @Override
    public void printDetails() {

        System.out.println("----------------------------------------");
        System.out.println("Resource Type: Digital Resource");

        // Calling protected method
        displayBasicDetails();

        System.out.println("Fine Rate    : Rs. 2/day");
        System.out.println("----------------------------------------");
    }
}


// Utility class
class InputValidator {

    // Validate Resource ID
    public static boolean validateResourceId(int resourceId) {
        return resourceId > 0;
    }

    // Validate Fine Days
    public static boolean validateFineDays(int overdueDays) {
        return overdueDays >= 0;
    }
}


// Main / Driver class
public class Main {

    public static void main(String[] args) {

        // Create at least five objects
        LibraryResource[] resources = new LibraryResource[5];

        resources[0] = new Book(
            101,
            "Data Structures",
            "Mark Allen"
        );

        resources[1] = new Book(
            102,
            "Operating Systems",
            "Galvin"
        );

        resources[2] = new DigitalResource(
            103,
            "Java Programming",
            "James Gosling"
        );

        resources[3] = new DigitalResource(
            104,
            "Artificial Intelligence",
            "Russell"
        );

        resources[4] = new Book(
            105,
            "Computer Networks",
            "Kurose"
        );


        // Overdue days for each resource
        int[] overdueDays = {
            4,
            2,
            6,
            3,
            5
        };


        // Display library name
        System.out.println("==========================================");
        System.out.println("       " + LibraryResource.getLibraryName());
        System.out.println("==========================================");


        // Display complete details
        System.out.println("\nCOMPLETE RESOURCE DETAILS\n");

        for (int i = 0; i < resources.length; i++) {

            // Validate Resource ID
            if (!InputValidator.validateResourceId(
                    resources[i].getResourceId())) {

                System.out.println("Invalid Resource ID");
                continue;
            }

            // Print details
            Printable printable =
                    (Printable) resources[i];

            printable.printDetails();

            // Display overdue days
            System.out.println(
                "Overdue Days : " + overdueDays[i]
            );

            // Calculate fine
            if (InputValidator.validateFineDays(
                    overdueDays[i])) {

                double fine =
                    resources[i].calculateFine(overdueDays[i]);

                System.out.println(
                    "Fine         : Rs. " + fine
                );

            } else {

                System.out.println("Invalid Fine Days");
            }

            System.out.println();
        }


        // Calculate total fine
        double totalFine = 0;

        for (int i = 0; i < resources.length; i++) {

            if (InputValidator.validateFineDays(
                    overdueDays[i])) {

                totalFine +=
                    resources[i].calculateFine(
                        overdueDays[i]
                    );
            }
        }


        // Display total fine
        System.out.println("==========================================");
        System.out.println(
            "Total Fine of All Resources: Rs. "
            + totalFine
        );
        System.out.println("==========================================");


        // Display total resources created
        LibraryResource.displayTotalResources();
    }
}
