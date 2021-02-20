import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Запускаем поток
        Task task = new Task();
        new Thread(task).start();

        // Когда нажмем на любую клавишу, программа перейдет на след строчку кода,тк затригерится этот кусок
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        // И дальше выполнится остановка
        task.stop();
        System.out.println("Main: " + Task.counter); // И проверяем счетчик только с мейна
        // без Volatile сработает только Main и программа будет идти дальше, поток не закрылся и строчка с Task не появится,
        // потому что isRunning закешировался в одном потоке, а "останавливается" в другом
    }
}
