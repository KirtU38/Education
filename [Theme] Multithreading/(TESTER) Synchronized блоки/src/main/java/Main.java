import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        ArrayList<Thread> threads = new ArrayList<>(); // Создаем Лист потоков
        for (int i = 0; i < 10; i++) {                 // Заполняем его 10 потоками
            Task task = new Task();                    // Создаем обьект с методом run()
            threads.add(new Thread(task));         // Можно вот так через Лямбду задать что каждый поток будет выполнять метод go()
        }

        threads.forEach(Thread::start);                // Запускаем все 10 потоков
    }
}

// Должен быть результать 10 раз по миллиону, однако без слова synchronized в методе потоки будут путаться так как
// получают доступ к одному Листу одновременно, однако с synchronized методом они будут получать доступ к методу
// по очереди (1 метод выполняется одновременно максимум 1 потоком)


// Если нас не пугает выполнение одного процесса(хоть и не совсем правильно) разными потоками, а пугают
// только ошибки с этим связанные, мы может сделать ПОТОКО-БЕЗОПАСНЫМ только БЛОК кода, а не весь метод
