import java.util.concurrent.atomic.AtomicInteger;

public class AtomicValue {
    private static AtomicInteger x = new AtomicInteger();

    public static void increment() {
        x.incrementAndGet();
    }
    public static int getX() {
        return x.intValue();
    }
}
