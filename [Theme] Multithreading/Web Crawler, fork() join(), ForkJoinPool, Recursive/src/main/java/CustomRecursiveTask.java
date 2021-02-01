import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;

public class CustomRecursiveTask extends RecursiveTask<Integer> {

    private int[] arr;

    static int iteration = 0;

    private static final int THRESHOLD = 5;

    public CustomRecursiveTask(int[] arr) {
        this.arr = arr;
    }

    // Аналог этого кода https://www.baeldung.com/java-fork-join
    protected Integer compute() {
        Integer result;
        if (arr.length > THRESHOLD) {
            int n = iteration++;

            List<CustomRecursiveTask> twoTasks = createSubtasks(); // Он возвращает не 2 массива, а 2 таска

            // invokeAll() форкает 2 ТАСКА, потом проходимся по каждому из 2 тасков и вытаскиваем Integer значение
            // из этих тасков потому что запускаем join() который запускает compute() у каждого таска и join()
            // возвращает результат этого таска, и через sum() он считает сумму результатов этих тасков
            /*result = ForkJoinTask.invokeAll(twoTasks)
                    .stream()
                    .mapToInt(ForkJoinTask::join)
                    .sum();*/

            // Это аналогия кода сверзу без использования streamAPI
            result = 0;
            for(CustomRecursiveTask task : ForkJoinTask.invokeAll(twoTasks)){
                System.out.println(task.join() + "   " + n);
                result += task.join();
            }
            // Конечный результат возвращается именно в этом методе
        } else {
            // А в этом сабтаски работают и считают суммы самых мелких массивов, а потом по дереву вверх передают
            // свои значения в таски которые в первом блоке if()
            result = processing(arr);
        }
        return result;
    }

    private List<CustomRecursiveTask> createSubtasks() {
        List<CustomRecursiveTask> dividedTasks = new ArrayList<>();

        dividedTasks.add(new CustomRecursiveTask(
                Arrays.copyOfRange(arr, 0, arr.length / 2)));

        dividedTasks.add(new CustomRecursiveTask(
                Arrays.copyOfRange(arr, arr.length / 2, arr.length)));
        System.out.println("Поделен на " + dividedTasks.get(0).arr.length + Arrays.toString(dividedTasks.get(0).arr) + " и " + dividedTasks.get(1).arr.length + Arrays.toString(dividedTasks.get(1).arr));

        return dividedTasks;
    }

    private Integer processing(int[] arr) {
        // Это аналог кода ниже
        int result = 0;

        for (int i : arr) {
            result += i;
        }
        return result;
        //return Arrays.stream(arr).sum();
    }

    public static void main(String[] args) {

        ForkJoinPool pool = new ForkJoinPool(4);

        int[] arr = new int[80];
        Arrays.fill(arr, 14);

        /*Arrays.stream(arr)
                .mapToDouble(e -> arr[e])
                .forEach(System.out::println);*/

        CustomRecursiveTask task = new CustomRecursiveTask(arr);
        System.out.println(pool.invoke(task));
    }
}