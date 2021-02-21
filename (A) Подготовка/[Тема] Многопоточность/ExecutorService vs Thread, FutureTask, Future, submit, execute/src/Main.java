public class Main {

    public static void main(String[] args) {

    }
}
// FutureTask при создании принимает в себя аргументом Callable, потом этот FutureTask мы может передать как аргумент
// в new Thread(futureTask), потому что FutureTask это Runnable

// new FutureTask(callable) -> new Thread(futureTask) -> futureTask.get() (как thread.join(), но вернем значение)


// Просто Future это БУДУЩИЙ результат работы метода executorService.submit()

// new ExecutorService(4) -> future = executorService.submit(callable) -> future.get() (как thread.join())

