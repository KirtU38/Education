import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.*;

public class ExecutorVsExecutorService {                         // Обьяснение в конце

    static int numOfThreadsOrIterations = 10;     // Переменная показывает сразу для обоих случаев кол-во операций
    static int numOfAccounts = 100;
    static int numOfCores = Runtime.getRuntime().availableProcessors();
    static long accountStartingBalance = 500000;
    static Bank bank = new Bank();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        fillBankWithAccounts();

        long start = System.currentTimeMillis();

        // Executor может быть только одним потоком, ExecutorService может управлять многими потоками
        // Executor может только execute(void Runnable)
        // ExecutorService может execute(void Runnable) и submit(<любой тип>Callable)
        // Ни Executor ни Service не останавливаются сами, для этого нужен метод shutdown()
        // У Executor его нет, у Service он есть

        /*
        // Executor
        // По сути просто создает поток и дает ему одну задачу
        // Возвращает void
        // Параметром берет Runnable
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runner(bank, numOfAccounts, start)); // Он просто выполняет void задачу, потому что метод run() у интерфейса Runnable всегда void
        */

        // ExecutorService
        // Может создавать "пулы" потоков
        // Возвращает void или любое другое значение(Callable)
        // Параметром берет Runnable или Callable
        ExecutorService service = Executors.newFixedThreadPool(4); // Создаем обьект Класса ExecutorService
        Future<Integer> randomNumber = service.submit(() -> {   // service.submit() принимает и Runnable и Callable, если принял Callable,
            int x = 0;                                          // значит метод что-то возвращает, передаем <Тип> в переменную Класса Future
            for (int i = 0; i < 100; i++) {                     // По сути Future это переменная любого типа вот и всё, просто она создана для работы Callable и потоками
                x++;
            }
            return x;             // Просто инременти x до 100 и возвращаем значение
        });
        System.out.println(randomNumber.get()); // А так мы ПОЛУЧАЕМ значение из Future с помощью метода get()



        /*scanner.nextLine();
        System.out.println("Баланс банка " + Bank.bankBalance);

        printAllAccountsBalance();

        printSumOfAllAccounts();*/
    }

    public static void fillBankWithAccounts() {

        for (int i = 0; i < numOfAccounts; i++) {  // Заполнить банк 100 аккаунтами с 500.000 на счету у каждого
            String accountId = String.valueOf(i);
            bank.getAccounts().put(accountId, new Account(accountId, accountStartingBalance));
            Bank.bankBalance += accountStartingBalance;
        }
    }

    public static void createAndStartThreads() {

        ArrayList<Thread> threads = new ArrayList<>(); // Создаем и запускам потоки
        long start = System.currentTimeMillis();
        for (int i = 0; i < numOfThreadsOrIterations; i++) {
            threads.add(new Thread(new Runner(bank, numOfAccounts, start)));
        }
        threads.forEach(Thread::start);
    }

    public static void printAllAccountsBalance() {

        for (int i = 0; i < bank.getAccounts().size(); i++) { // Вывести все счета

            String id = String.valueOf(i);
            System.out.println("\"" + id + "\"" + " " + bank.getBalance(id));
        }
    }

    public static void printSumOfAllAccounts() {

        long checkBankBalance = 0;
        for (int i = 0; i < bank.getAccounts().size(); i++) { // Сложить все счета заново, должна получиться начальная сумма банка

            String id = String.valueOf(i);
            checkBankBalance = checkBankBalance + bank.getBalance(id);
            System.out.println(i + " Сложить все счета обратно " + checkBankBalance);
        }
    }
}