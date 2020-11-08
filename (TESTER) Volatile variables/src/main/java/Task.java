public class Task implements Runnable{

    static int counter;
    //static boolean isRunning;  // Обычная переменная
    static volatile boolean isRunning; // Слово volatile говорит "кэшировать эту переменную не нужно"

    public Task() {       // Задается значение в конструкторе, что поток идет
        isRunning = true;
    }
    public void run() {
        while(isRunning){  // Пока поток идет, инкрементим без остановки значение counter
            counter++;
        }
        System.out.println("Task: " + counter); // В конце выводим счетчик с пометкой Task
    }
    public void stop(){
        isRunning = false;
    }
}
