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

