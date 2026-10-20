import java.util.Optional;

public class StudentEmailDemo {

    public static void main(String[] args) {
        // 1. Create an Optional containing an email address
        Optional<String> activeEmail = Optional.of("student@example.com");

        // 2. Create an empty Optional
        Optional<String> missingEmail = Optional.empty();

        // 3. Display the email using ifPresent()
        System.out.print("3. Using ifPresent(): ");
        activeEmail.ifPresent(email -> System.out.println("Student Email: " + email));

        // 4. Use orElse() to provide a default email address
        String fallbackEmail = missingEmail.orElse("default.student@institute.edu");
        System.out.println("4. Using orElse(): " + fallbackEmail);

        // 5. Use orElseThrow() to handle a missing email value
        try {
            System.out.println("5. Attempting orElseThrow() on missingEmail:");
            missingEmail.orElseThrow(() -> new IllegalArgumentException("Email address is not registered!"));
        } catch (IllegalArgumentException e) {
            System.out.println("   Caught expected exception: " + e.getMessage());
        }
    }
}
