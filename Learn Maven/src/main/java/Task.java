import java.util.ArrayList;

public class Task implements Runnable{

    static ArrayList<Double> numbers = new ArrayList<>(); // Создаем лист чисел

    public void go() {                   // Через переменную
        for (int i = 0; i < 1000000; i++) {               // Добавляем миллион рандомных чисел в Лист numbers
            synchronized (numbers) {
                numbers.add(Math.random() / Math.random());
            }
        }
        System.out.println(numbers.size());               // По окончанию добавления миллиона чисел выводим размер Листа
        numbers.clear();                                  // Очищаем по окончания
    }

    public void go1() {                   // Через весь класс
        for (int i = 0; i < 1000000; i++) {
            synchronized (Task.class) {
                numbers.add(Math.random() / Math.random());
            }
        }
        System.out.println(numbers.size());
        numbers.clear();
    }

    public void go2() {                   //
        for (int i = 0; i < 1000000; i++) {
            synchronized (this) {         // Слово this означает "обьект этого класса"
                numbers.add(Math.random() / Math.random());
            }
        }
        System.out.println(numbers.size());
        numbers.clear();
    }

    @Override
    public void run() {
        //go();
        //go1();
        go2();
    }
}
