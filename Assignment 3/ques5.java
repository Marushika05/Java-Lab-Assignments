import java.util.Scanner;

// Exception for insufficient balance
class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}

// Exception for invalid amount
class InvalidAmountException extends Exception {
    public InvalidAmountException(String message) {
        super(message);
    }
}

// Exception for invalid account number
class AccountNotFoundException extends Exception {
    public AccountNotFoundException(String message) {
        super(message);
    }
}

class BankAccount {

    private int accountNumber;
    private double balance;

    BankAccount(int accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    // Deposit
    void deposit(double amount) throws InvalidAmountException {

        if (amount <= 0) {
            throw new InvalidAmountException(
                "Deposit amount must be greater than zero."
            );
        }

        balance += amount;

        System.out.println("Successfully deposited: ₹" + amount);
    }

    // Withdrawal
    void withdraw(double amount)
            throws InvalidAmountException, InsufficientBalanceException {

        if (amount <= 0) {
            throw new InvalidAmountException(
                "Withdrawal amount must be greater than zero."
            );
        }

        if (amount > balance) {
            throw new InsufficientBalanceException(
                "Insufficient balance. Available balance: ₹" + balance
            );
        }

        balance -= amount;

        System.out.println("Successfully withdrawn: ₹" + amount);
    }

    void displayBalance() {
        System.out.println("Current Balance: ₹" + balance);
    }
}

public class BankingApplication {

    static BankAccount findAccount(BankAccount[] accounts, int accountNumber)
            throws AccountNotFoundException {

        for (BankAccount account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }

        throw new AccountNotFoundException(
            "Account " + accountNumber + " not found."
        );
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        BankAccount[] accounts = {
            new BankAccount(101, 5000),
            new BankAccount(102, 10000)
        };

        try {

            System.out.print("Enter account number: ");
            int accountNumber = sc.nextInt();

            // Exception propagation:
            // findAccount() throws exception → main() catches it
            BankAccount account =
                findAccount(accounts, accountNumber);

            System.out.print("Enter deposit amount: ");
            double depositAmount = sc.nextDouble();

            // Exception can propagate from deposit()
            // to main()
            account.deposit(depositAmount);

            System.out.print("Enter withdrawal amount: ");
            double withdrawalAmount = sc.nextDouble();

            account.withdraw(withdrawalAmount);

            account.displayBalance();

        } catch (AccountNotFoundException e) {

            System.out.println("Error: " + e.getMessage());

        } catch (InvalidAmountException e) {

            System.out.println("Error: " + e.getMessage());

        } catch (InsufficientBalanceException e) {

            System.out.println("Error: " + e.getMessage());

        } finally {

            System.out.println("Banking transaction completed.");

        }

        sc.close();
    }
}
