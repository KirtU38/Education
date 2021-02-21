import java.util.*;
import java.util.concurrent.*;

public class ThreadJoinWithExecutorService {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        // Задача просто заполнить Лист

        // ExecutorService
        ExecutorService service = Executors.newFixedThreadPool(4);
        // Создаем наш task типа Runnable
        Runnable taskRunnable = () -> {
            System.out.println(Thread.currentThread().getName());
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < 4_000_000; i++) {
                map.put(i, i);
            }
            System.out.println(map.size() + " " + Thread.currentThread().getName());
        };

        // Здесь уже запускается наш task
        // thread.start()
        Future<?> future = service.submit(taskRunnable);
        // future.get() это аналог thread.join(), то есть тред ждет пока этот future вернет нам результат и умрет,
        // от void Runnable наш future вернет null
        // thread.join()
        future.get();
        System.out.println("DONE");
        service.shutdown();
    }
}













