import java.io.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static void main(String[] args) throws IOException {

        Test test = new Test(0);

        new Thread(new Runner(test, 10), "First").start();
        new Thread(new Runner(test, 20), "Second").start();
        new Thread(new Runner(test, 30), "Third").start();
        new Thread(new Runner(test, 40), "Fourth").start();
    }
}










