import lombok.SneakyThrows;

public class Task implements Runnable {

  private int counter;
  private volatile boolean isRunning; // Слово volatile говорит "кэшировать эту переменную не нужно"

  // Задается значение в конструкторе, что поток идет
  public Task() {
    isRunning = true;
  }

  // Пока поток идет, инкрементим без остановки значение counter
  @SneakyThrows
  public void run() {
    while (isRunning) {
      counter++;
    }
    System.out.println("Task: " + counter);
  }

  public void stop() {
    isRunning = false;
  }
  
  public int getCounter() {
    return counter;
  }
}
