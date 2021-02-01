import java.util.concurrent.atomic.AtomicInteger;

public class StandardValue {

    private static int x;

    public static void increment() {
        x = x + 1;
    }
    public static int getX() {
        return x;
    }
}
