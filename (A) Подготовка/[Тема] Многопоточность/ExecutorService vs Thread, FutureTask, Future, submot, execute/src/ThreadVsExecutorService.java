import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ThreadVsExecutorService {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        // Задача заполнить Лист 2мил элементов

        // Через ручные thread`ы
        List<Thread> listOfThreads = new ArrayList<>();

        // Наш Таск
        Runnable taskRunnable = () -> {
            System.out.println(Thread.currentThread().getName());
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < 1_000_000; i++) {
                list.add(i);
            }
            System.out.println(list.size() + " " + Thread.currentThread().getName());
        };
        // Создаем 20 тредов
        for (int i = 0; i < 20; i++) {
            listOfThreads.add(new Thread(taskRunnable));
        }
        // Запускаем и join()
        listOfThreads.forEach(Thread::start);
        for (Thread thread : listOfThreads) {
            thread.join();
        }
        System.out.println("Threads all done\n");



        // Через ExecutorService
        ExecutorService service = Executors.newFixedThreadPool(4);
        // Создаем Лист Futer`ов
        List<Future<?>> listOfFutures = new ArrayList<>();
        // Добавляем и одновременно запускаем все таски
        for (int i = 0; i < 20; i++) {
            listOfFutures.add(service.submit(taskRunnable));
        }
        // join() все такси методом future.get()
        for (Future<?> future : listOfFutures) {
            future.get();
        }
        System.out.println("ExecutorService DONE");
    }
}
// Плюсы ExecutorService в том, что у нас нет безконтрольного создания потоков, потоки в Джава соответствуют потокам
// в Операционной системе, поэтому если мы каждый раз будем создавать new Thread для каждой задачи, то можем сильно
// загрузить систему, в то время как в ExecutorService мы заранее указываем максимальное кол-во потоков и потоки
// будут по-очереди выполнять данные Сервису задачи(таски)

// По результатам теста выше можно увидеть, что реализация через Треды создала аж 20 тредов, однако ExecutorService
// выполнял таски по-очереди, по мере освобождения тредов от работы













