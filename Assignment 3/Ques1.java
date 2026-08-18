// Online Java Compiler
// Use this editor to write, compile and run your Java code online

class Main {
    public static void main(String[] args) {
       int n=10;
       int m=0;

       try{
        int ans=n/m;
        System.out.println("ans="+ ans);
       }
       catch(ArithmeticException e){
        System.out.println("Error division by 0!!");
       }

        try{
        int arr[]={10,30,70};
        System.out.println("ans="+ arr[5]);
       }
       catch(ArrayIndexOutOfBoundsException e){
        System.out.println("Array Index Out Of Bound Exception: invalid index");
       }

        try{
        String str="abc";
        int num= Integer.parseInt(str);
        System.out.println("number"+ num);
       }
       catch(NumberFormatException e){
        System.out.println("NumberFormatException: invalid number format");
       }

       try{
        String str=null;
        System.out.println(str.length());
       }
       catch(NullPointerException e){
        System.out.println("NullPointerException: string is null");
       }

       finally{
        System.out.println("Exceptiopn handling is completed");
       }
    }
}
