import java.util.ArrayList;
import java.util.Scanner;

// Parent exception
class ShoppingException extends Exception {
    public ShoppingException(String message) {
        super(message);
    }
}

// Child exceptions
class ProductNotFoundException extends ShoppingException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}

class InvalidQuantityException extends ShoppingException {
    public InvalidQuantityException(String message) {
        super(message);
    }
}

class EmptyCartException extends ShoppingException {
    public EmptyCartException(String message) {
        super(message);
    }
}

class InsufficientStockException extends ShoppingException {
    public InsufficientStockException(String message) {
        super(message);
    }
}

class PaymentException extends ShoppingException {
    public PaymentException(String message) {
        super(message);
    }
}


// Product class
class Product {

    int id;
    String name;
    double price;
    int stock;

    Product(int id, String name, double price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
}


// Shopping Cart
class ShoppingCart {

    ArrayList<Product> cart = new ArrayList<>();

    // Search product
    Product searchProduct(Product[] products, int id)
            throws ProductNotFoundException {

        for (Product p : products) {

            if (p.id == id) {
                return p;
            }
        }

        throw new ProductNotFoundException(
            "Product with ID " + id + " not found."
        );
    }

    // Add product
    void addProduct(Product product, int quantity)
            throws InvalidQuantityException,
                   InsufficientStockException {

        if (quantity <= 0) {
            throw new InvalidQuantityException(
                "Quantity must be greater than zero."
            );
        }

        if (quantity > product.stock) {
            throw new InsufficientStockException(
                "Not enough stock for " + product.name
            );
        }

        // Add the product
        for (int i = 0; i < quantity; i++) {
            cart.add(product);
        }

        product.stock -= quantity;

        System.out.println(
            quantity + " " + product.name + "(s) added to cart."
        );
    }

    // Remove product
    void removeProduct(int id)
            throws ProductNotFoundException {

        for (Product p : cart) {

            if (p.id == id) {

                cart.remove(p);

                System.out.println(
                    p.name + " removed from cart."
                );

                return;
            }
        }

        throw new ProductNotFoundException(
            "Product is not present in cart."
        );
    }

    // Calculate total
    double calculateTotal() throws EmptyCartException {

        if (cart.isEmpty()) {
            throw new EmptyCartException(
                "Cart is empty."
            );
        }

        double total = 0;

        for (Product p : cart) {
            total += p.price;
        }

        return total;
    }

    // Payment
    void payment(double amount)
            throws EmptyCartException, PaymentException {

        double total = calculateTotal();

        if (amount < total) {
            throw new PaymentException(
                "Payment failed. Required amount: ₹" + total
            );
        }

        System.out.println(
            "Payment successful! Amount paid: ₹" + amount
        );

        cart.clear();
    }
}


// Main class
public class ShoppingApplication {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Product[] products = {
            new Product(101, "Laptop", 60000, 5),
            new Product(102, "Mouse", 1000, 10),
            new Product(103, "Keyboard", 2000, 8)
        };

        ShoppingCart cart = new ShoppingCart();

        try {

            // Search
            System.out.print("Enter product ID: ");
            int id = sc.nextInt();

            Product product =
                cart.searchProduct(products, id);

            System.out.println(
                "Product found: " + product.name
            );

            // Add
            System.out.print("Enter quantity: ");
            int quantity = sc.nextInt();

            cart.addProduct(product, quantity);

            // Display total
            double total = cart.calculateTotal();

            System.out.println(
                "Cart Total: ₹" + total
            );

            // Payment
            System.out.print("Enter payment amount: ");
            double amount = sc.nextDouble();

            cart.payment(amount);

        } catch (ProductNotFoundException e) {

            System.out.println(
                "Product Error: " + e.getMessage()
            );

        } catch (InvalidQuantityException e) {

            System.out.println(
                "Quantity Error: " + e.getMessage()
            );

        } catch (InsufficientStockException e) {

            System.out.println(
                "Stock Error: " + e.getMessage()
            );

        } catch (EmptyCartException e) {

            System.out.println(
                "Cart Error: " + e.getMessage()
            );

        } catch (PaymentException e) {

            System.out.println(
                "Payment Error: " + e.getMessage()
            );

        } finally {

            System.out.println(
                "Shopping operation completed."
            );
        }

        sc.close();
    }
}
