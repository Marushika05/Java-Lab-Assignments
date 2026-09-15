import java.util.Scanner;

// Custom exceptions
class InvalidUsernameException extends Exception {
    public InvalidUsernameException(String message) {
        super(message);
    }
}

class InvalidPasswordException extends Exception {
    public InvalidPasswordException(String message) {
        super(message);
    }
}

class AccountLockedException extends Exception {
    public AccountLockedException(String message) {
        super(message);
    }
}

public class LoginSystem {

    static final String CORRECT_USERNAME = "admin";
    static final String CORRECT_PASSWORD = "1234";

    static final int MAX_ATTEMPTS = 3;

    static int failedAttempts = 0;
    static boolean locked = false;

    static void login(String username, String password)
            throws InvalidUsernameException,
                   InvalidPasswordException,
                   AccountLockedException {

        // Check if account is locked
        if (locked) {
            throw new AccountLockedException(
                "Account is locked. Too many failed attempts."
            );
        }

        // Check username
        if (!username.equals(CORRECT_USERNAME)) {
            failedAttempts++;

            if (failedAttempts >= MAX_ATTEMPTS) {
                locked = true;
            }

            throw new InvalidUsernameException(
                "Invalid username."
            );
        }

        // Check password
        if (!password.equals(CORRECT_PASSWORD)) {
            failedAttempts++;

            if (failedAttempts >= MAX_ATTEMPTS) {
                locked = true;
            }

            throw new InvalidPasswordException(
                "Invalid password."
            );
        }

        // Successful login
        failedAttempts = 0;

        System.out.println("Login successful!");
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (!locked) {

            try {

                System.out.print("\nEnter username: ");
                String username = sc.nextLine();

                System.out.print("Enter password: ");
                String password = sc.nextLine();

                login(username, password);

                break;

            } catch (InvalidUsernameException e) {

                System.out.println("Error: " + e.getMessage());

            } catch (InvalidPasswordException e) {

                System.out.println("Error: " + e.getMessage());

            } catch (AccountLockedException e) {

                System.out.println("Error: " + e.getMessage());

            } finally {

                System.out.println(
                    "Failed attempts: " + failedAttempts
                );
            }
        }

        if (locked) {
            System.out.println(
                "\nAccount has been permanently locked."
            );
        }

        sc.close();
    }
}
