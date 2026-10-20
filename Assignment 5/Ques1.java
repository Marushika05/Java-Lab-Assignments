public class NumericSumDemo {

    // Generic method with bounded type parameter T extends Number
    public static <T extends Number> double calculateSum(T[] array) {
        double sum = 0.0;
        for (T element : array) {
            if (element != null) {
                sum += element.doubleValue();
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        Integer[] intArr = {10, 20, 30, 40, 50};
        Float[] floatArr = {1.5f, 2.5f, 3.5f};
        Double[] doubleArr = {12.25, 18.75, 4.5};

        System.out.println("Integer Array Sum: " + calculateSum(intArr));
        System.out.println("Float Array Sum:   " + calculateSum(floatArr));
        System.out.println("Double Array Sum:  " + calculateSum(doubleArr));
    }
}
