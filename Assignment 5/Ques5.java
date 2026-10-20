import java.util.Objects;
import java.util.Optional;

public class GenericUtils {

    // 1. Checks whether two generic objects are equal
    public static <T> boolean isEqual(T a, T b) {
        return Objects.equals(a, b);
    }

    // 2. Returns the contained value or defaultValue
    public static <T> T getDefault(Optional<T> value, T defaultValue) {
        return value != null ? value.orElse(defaultValue) : defaultValue;
    }

    // 3. Returns the larger of two Comparable objects
    public static <T extends Comparable<T>> T max(T a, T b) {
        Objects.requireNonNull(a, "Argument 'a' cannot be null");
        Objects.requireNonNull(b, "Argument 'b' cannot be null");
        return (a.compareTo(b) >= 0) ? a : b;
    }

    // 4. Checks whether an object is null using java.util.Objects
    public static boolean isNull(Object obj) {
        return Objects.isNull(obj);
    }

    public static void main(String[] args) {
        System.out.println("--- 1. isEqual() ---");
        System.out.println("Strings 'Java' & 'Java': " + GenericUtils.isEqual("Java", "Java"));
        System.out.println("Integers 100 & 200:      " + GenericUtils.isEqual(100, 200));

        System.out.println("\n--- 2. getDefault() ---");
        Optional<String> presentOpt = Optional.of("Active Session");
        Optional<String> emptyOpt = Optional.empty();
        System.out.println("Present Optional: " + GenericUtils.getDefault(presentOpt, "Guest"));
        System.out.println("Empty Optional:   " + GenericUtils.getDefault(emptyOpt, "Guest"));

        System.out.println("\n--- 3. max() ---");
        System.out.println("Max of Integers (42, 99):      " + GenericUtils.max(42, 99));
        System.out.println("Max of Strings ('Apple', 'Banana'): " + GenericUtils.max("Apple", "Banana"));

        System.out.println("\n--- 4. isNull() ---");
        String regularStr = "Present";
        String nullStr = null;
        System.out.println("Is regularStr null? " + GenericUtils.isNull(regularStr));
        System.out.println("Is nullStr null?    " + GenericUtils.isNull(nullStr));
    }
}
