// Online Java Compiler
// Use this editor to write, compile and run your Java code online
public class NestedTryExample {
    public static void main(String[] args) {
        // Outer try block
        try {
            System.out.println("Outer block started.");
            int[] numbers = {1, 2, 3};

            // Inner try block
            try {
                System.out.println("Inner block started.");
                // This throws an ArithmeticException
                int result = numbers[1] / 0; 
            } 
            // Handles specific math errors locally
            catch (ArithmeticException e) { 
                System.out.println("Inner Catch: Cannot divide by zero.");
            }

            // Another inner try block
            try {
                // This throws an ArrayIndexOutOfBoundsException
                System.out.println(numbers[5]); 
            }
            // Handled locally? No, this catch only intercepts NullPointerExceptions
            catch (NullPointerException e) {
                System.out.println("Inner Catch: Null pointer encountered.");
            }
        }
       
        // Enclosing catch handles errors that bubble up from the inner block
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Outer Catch: Handled array index out of bounds error.");
        } 
        catch (Exception e) {
            System.out.println("Outer Catch: Handled a generic error.");
        }
        
        
        System.out.println("Execution continues...");
    }
}
