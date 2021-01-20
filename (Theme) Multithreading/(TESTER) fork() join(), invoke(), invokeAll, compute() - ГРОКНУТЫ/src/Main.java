import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class Main {

    static int sizeOfArray = 100000000;

    public static void main(String[] args) {

        ForkJoinPool pool = new ForkJoinPool(8);

        long[] array = new long[sizeOfArray];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        System.out.println("FILLED");


        Square task = new Square(array, 0, array.length);
        long start = System.currentTimeMillis();                            // С fork() без join() 10 ms 100 mil (АХУЕТЬ)
        //pool.invoke(task);                                                  // 100  100 mil 8 threads - Pool

        task.invoke();                                                      // 150 ms 100 mil - CommonPool
        System.out.println(System.currentTimeMillis() - start + " ms");

        for (int i = 10000; i < 10020; i++) {
            System.out.println(array[i]);
        }

        /*long start = System.currentTimeMillis();
        squareRoot(array);
        System.out.println(System.currentTimeMillis() - start + " ms"); // 200 ms 100 mil*/
    }

    public static void squareRoot(long[] array) {

        for (int i = 0; i < array.length; i++) {
            array[i] = array[i] * array[i];
        }
    }
}
