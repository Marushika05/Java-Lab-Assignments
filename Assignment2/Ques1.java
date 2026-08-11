import java.util.Scanner;
//online food delivery that manages types odf orders
//need to make packages and classes

//creating abstract class
abstract class FoodOrder{
    private int orderid;
    private String customername;
    private double ammount;


//static memeber
private static String restroname="FoodBazaar";

//static obj counter
private static int totalorders=0;

//constuctor
public FoodOrder(int orderid, String customername, double ammount){
    this.orderid=orderid;
    this.customername=customername;
    this.amount=amount;
    totalorders++;
}
//getters and setters
public int getorderid(){
    return orderid;

}

public void setorderid(int orderid){
    this.orderid=orderid;
}

public String getcustomername(){
    return customername;
}

public void setcustomername(String customername){
    this.customername=customername;
}

public double getamount(){
return amount;
}

public void setamount(double amount){
    this.amount=amount;
}

public static getrestroname(){
    return restroname;
}

public static void setrestroname(string restroname){
    FoodOrder.restroname=restroname;
    //since its a static member thus we need class to call it as static belongs to class;
}

//abstract method
public abstract double calc_deli_charge();//delivery charge

//static method
public static void display_total_order(){
    System.out.println("Total orders created: "+totalorders);
}

}

//creatinginterface
interface Discountable{
    double applydisc();
}

//regular order
class RegularOrder extends FoodOrder implements Discountable{
    public RegularOrder(int orderid, String customername, double amount){
        super(orderid,customername,amount);
    }

    //d3livery charge =80/-

    @Override
    public double calc_deli_charge(){
        return 80;
    }

    //discount 10 percent
    @Override
    public double applydisc(){
        return getamount()*0.10;
    }
}

//Premium order
class PremiumOrder extends FoodOrder implements Discountable{
    public PremiumOrder(int orderid, String customername, double amount){
        super(orderid,customername,amount);
    }

    //d3livery charge =80/-

    @Override
    public double calc_deli_charge(){
        return 50;
    }

    //discount 10 percent
    @Override
    public double applydisc(){
        return getamount()*0.15;
    }
}

//utility class
class OrderUtility{
    //vlaidate amount
    public static boolean validate_amt(double amount){
        return amount>0;
    }

    //val customername
    public static boolean validate_customer_name(String name){
        return name!=null && !name.trim().isEmpty();
    }

    //generate order summary 
    public static void gen_ord_summary(FoodOrder order)
    {
        double discount=((Discountable)order).applydisc();
        double delicharge=order.calc_deli_charge();
        double finalamount=order.getamount()-discount+delicharge;
 //sysytem p[rint commands
 
    }
}

//driver class
public class Main{
    public static void main (String[] args){
        //cretae an array of food orders

        FoodOrder[] orders =new FoodOrder[6];

        //creating new orders
        // order[0]=

        ///print cmd
        

    //display bill for every roder
    for(FoodOrder order :orders){
        if(!OrderUtility.validate_customer_name(order.getcustomername())){
            System.err.println("Invalid Custoemr name");
            continue;
        }

        if(!OrderUtility.validate_amt(order.getamount())){
            System.err.println("Invalid Amount");
            continue;
        }

        //generate bil
        OrderUtility.gen_ord_summary(order);
    }

    //display total nu,bers of orders
    System.out.println();
    FoodOrder.display_total_order();
    }
}
