import java.util.Scanner;
import java.util.InputMismatchException;

public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Prompt user for inputs
            System.out.print("Enter the first number: ");
            double num1 = scanner.nextDouble();

            System.out.print("Enter an operator (+, -, *, /): ");
            char operator = scanner.next().charAt(0);

            System.out.print("Enter the second number: ");
            double num2 = scanner.nextDouble();

            // Validate operator
            if (operator != '+' && operator != '-' && operator != '*' && operator != '/') {
                throw new IllegalArgumentException("Invalid operator '" + operator + "'. Only +, -, *, and / are allowed.");
            }

            // Validate division by zero
            if (operator == '/' && num2 == 0) {
                throw new ArithmeticException("Division by zero is not allowed.");
            }

            // Perform calculation
            double result = 0;
            switch (operator) {
                case '+': result = num1 + num2; break;
                case '-': result = num1 - num2; break;
                case '*': result = num1 * num2; break;
                case '/': result = num1 / num2; break;
            }

            System.out.println("Result: " + num1 + " " + operator + " " + num2 + " = " + result);

        } // Separate exception handlers
        catch (InputMismatchException e) {
            System.out.println("Input Error: Invalid numeric input. Please enter valid numbers.");
        } 
        catch (IllegalArgumentException e) {
            System.out.println("Operator Error: " + e.getMessage());
        } 
        catch (ArithmeticException e) {
            System.out.println("Math Error: " + e.getMessage());
        } 
        catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        } 
        finally {
            scanner.close();
        }
    }
}
