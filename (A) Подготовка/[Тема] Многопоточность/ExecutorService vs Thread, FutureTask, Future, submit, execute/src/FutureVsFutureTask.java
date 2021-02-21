import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class FutureVsFutureTask {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        // Runnable и Callable
        Runnable runner = ()-> System.out.println("Im running");
        Callable<String> caller = ()-> {
            return "Hello Im calling";
        };

        // FutureTask`и
        FutureTask<?> runnableFuture = new FutureTask<>(runner, null);
        new Thread(runnableFuture).start();
        runnableFuture.get();

        FutureTask<?> callableFuture = new FutureTask<>(caller);
        new Thread(callableFuture).start();
        System.out.println(callableFuture.get());

        System.out.println("Сделано");

        // С ExecutorService
        ExecutorService service = Executors.newFixedThreadPool(4);
        Future<String> future = service.submit(caller);
        Future<?> future1 = service.submit(runner);
    }
}
// FutureTask это имплементация интерфейса Future, но отличие что FutureTask можно создать как обьект(instatiate),
// FutureTask можно передать в Thread как Runnable
// FutureTask принимает и Runnable и Callable

// Future хорош тем, что в него можно передать submit(callable) или submit(runnable) и управлять отдельно тасками
// как тредами по сути

// Начать Callable
// Future<String> future = service.submit(caller);

// Начать Runnable
// Future<?> future = service.submit(runner);

// Чтобы сделать join(), просто нужно написать future.get(), то есть этот код:
// Future<?> future = service.submit(runner);
// future.get()
// System.out.println("Сделано");

// начнется таск runner -> подождем выполнения -> только потом напечатаем "Сделано"













