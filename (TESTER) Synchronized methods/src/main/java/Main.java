import java.util.ArrayList;

public class Main {

    static ArrayList<Double> numbers = new ArrayList<>(); // Создаем лист чисел

    public static void main(String[] args) {

        ArrayList<Thread> threads = new ArrayList<>(); // Создаем Лист потоков
        for (int i = 0; i < 10; i++) {                 // Заполняем его 10 потоками
            threads.add(new Thread(Main::go));         // Можно вот так через Лямбду задать что каждый поток будет выполнять метод go()
        }

        threads.forEach(Thread::start);                // Запускаем все 10 потоков
    }
    // Должен быть результать 10 раз по миллиону, однако без слова synchronized в методе потоки будут путаться так как
    // получают доступ к одному Листу одновременно, однако с synchronized методом они будут получать доступ к методу
    // по очереди (1 метод выполняется одновременно максимум 1 потоком)


    public static void go() {
        for (int i = 0; i < 1000000; i++) {               // Добавляем миллион рандомных чисел в Лист numbers
            numbers.add(Math.random() / Math.random());
        }
        System.out.println(numbers.size());               // По окончанию добавления миллиона чисел выводим размер Листа
        numbers.clear();                                  // Очищаем по окончания
    }

    /*public static synchronized void go() {                             // Синхронизированный метод
        for (int i = 0; i < 1000000; i++) {               // Добавляем миллион рандомных чисел в Лист numbers
            numbers.add(Math.random() / Math.random());
        }
        System.out.println(numbers.size());               // По окончанию добавления миллиона чисел выводим размер Листа
        numbers.clear();                                  // Очищаем по окончания
    }*/
}
