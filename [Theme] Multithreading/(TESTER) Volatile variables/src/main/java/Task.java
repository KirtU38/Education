public class Task implements Runnable {

    static int counter;
    static volatile boolean isRunning; // Слово volatile говорит "кэшировать эту переменную не нужно"

    // Задается значение в конструкторе, что поток идет
    public Task() {
        isRunning = true;
    }

    // Пока поток идет, инкрементим без остановки значение counter
    public void run() {
        while (isRunning) {
            counter++;
        }
        System.out.println("Task: " + counter);
    }

    public void stop() {
        isRunning = false;
    }
}
