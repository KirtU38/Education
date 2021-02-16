import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class Main {

    static int sizeOfArray = 100_000_000;
    public static final ForkJoinPool FORK_JOIN_POOL = new ForkJoinPool();

    public static void main(String[] args) {

        // Заполняем массив
        long[] array = new long[sizeOfArray];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        System.out.println("FILLED");

        // Создаем таск
        Square task = new Square(array, 0, array.length);

        long start = System.currentTimeMillis();
        // Разные способы запустить таск:

        // Через обьект ForkJoinPool`а, это по сути правильный метод
        FORK_JOIN_POOL.invoke(task); // 250 ms

        // Через метод самого RecursiveAction`а, но тогда используется main thread
        // (пока внутри самого таск не будет использован fork() или invokeAll())
        //task.invoke();

        // Через compute() метод RecursiveAction`а, по сути то же самое, что и compute()
        //task.compute();
        System.out.println(System.currentTimeMillis() - start + " ms");
    }

    // Для теста скорости без ForkJoin`а
    public static void squareRoot(long[] array) {

        for (int i = 0; i < array.length; i++) {
            array[i] = array[i] * array[i];
        }
    }
}
// Программа получает на вход массив и возводит каждое число в квадрат
//
