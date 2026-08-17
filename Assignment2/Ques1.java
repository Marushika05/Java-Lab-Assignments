// online food delivery that manages types of orders
// need to make packages and classes

// creating abstract class
abstract class FoodOrder {

    private int orderid;
    private String customername;
    private double amount;

    // static member
    private static String restroname = "FoodBazaar";

    // static object counter
    private static int totalorders = 0;

    // constructor
    public FoodOrder(int orderid, String customername, double amount) {
        this.orderid = orderid;
        this.customername = customername;
        this.amount = amount;
        totalorders++;
    }

    // getters and setters
    public int getorderid() {
        return orderid;
    }

    public void setorderid(int orderid) {
        this.orderid = orderid;
    }

    public String getcustomername() {
        return customername;
    }

    public void setcustomername(String customername) {
        this.customername = customername;
    }

    public double getamount() {
        return amount;
    }

    public void setamount(double amount) {
        this.amount = amount;
    }

    public static String getrestroname() {
        return restroname;
    }

    public static void setrestroname(String restroname) {
        FoodOrder.restroname = restroname;
    }

    // abstract method
    public abstract double calc_deli_charge();

    // static method
    public static void display_total_order() {
        System.out.println("Total orders created: " + totalorders);
    }
}


// creating interface
interface Discountable {
    double applydisc();
}


// Regular order
class RegularOrder extends FoodOrder {

    public RegularOrder(int orderid, String customername, double amount) {
        super(orderid, customername, amount);
    }

    // delivery charge = 80
    @Override
    public double calc_deli_charge() {
        return 80;
    }

    // discount = 10 percent
    public double applydisc() {
        return getamount() * 0.10;
    }
}


// Premium order
class PremiumOrder extends FoodOrder {

    public PremiumOrder(int orderid, String customername, double amount) {
        super(orderid, customername, amount);
    }

    // delivery charge = 50
    @Override
    public double calc_deli_charge() {
        return 50;
    }

    // discount = 15 percent
    public double applydisc() {
        return getamount() * 0.15;
    }
}


// Utility class
class OrderUtility {

    // validate amount
    public static boolean validate_amt(double amount) {
        return amount > 0;
    }

    // validate customer name
    public static boolean validate_customer_name(String name) {
        return name != null && !name.trim().isEmpty();
    }

    // generate order summary
    public static void gen_ord_summary(FoodOrder order) {

        double discount;

        if (order instanceof RegularOrder) {
            discount = order.getamount() * 0.10;
        } else {
            discount = order.getamount() * 0.15;
        }

        double delicharge = order.calc_deli_charge();

        double finalamount =
                order.getamount() - discount + delicharge;

        System.out.println("--------------------------------------------");
        System.out.println("Order ID        : " + order.getorderid());
        System.out.println("Customer Name   : " + order.getcustomername());
        System.out.println("Amount          : Rs. " + order.getamount());
        System.out.println("Discount        : Rs. " + discount);
        System.out.println("Delivery Charge : Rs. " + delicharge);
        System.out.println("Final Payable   : Rs. " + finalamount);
        System.out.println("--------------------------------------------");
    }
}


// Driver class
public class Main {

    public static void main(String[] args) {

        // create an array of FoodOrder
        FoodOrder[] orders = new FoodOrder[6];

        // creating six orders
        orders[0] = new RegularOrder(101, "Rahul", 500);
        orders[1] = new PremiumOrder(102, "Priya", 1200);
        orders[2] = new RegularOrder(103, "Aman", 800);
        orders[3] = new PremiumOrder(104, "Neha", 1500);
        orders[4] = new RegularOrder(105, "Karan", 650);
        orders[5] = new PremiumOrder(106, "Simran", 2000);

        // display restaurant
        System.out.println("============================================");
        System.out.println("             " + FoodOrder.getrestroname());
        System.out.println("============================================");

        // display bill for every order
        for (FoodOrder order : orders) {

            if (!OrderUtility.validate_customer_name(
                    order.getcustomername())) {

                System.out.println("Invalid Customer Name");
                continue;
            }

            if (!OrderUtility.validate_amt(order.getamount())) {

                System.out.println("Invalid Amount");
                continue;
            }

            // generate bill
            OrderUtility.gen_ord_summary(order);
        }

        // display total number of orders
        System.out.println();
        FoodOrder.display_total_order();
    }
}
