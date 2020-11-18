import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainThreadPool {                         // Обьяснение в конце

    static int numOfThreadsOrIterations = 500;     // Переменная показывает сразу для обоих случаев кол-во операций
    static int numOfAccounts = 100;
    static int numOfCores = Runtime.getRuntime().availableProcessors();
    static long accountStartingBalance = 500000;
    static Bank bank = new Bank();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        fillBankWithAccounts();

        long start = System.currentTimeMillis();

        // Есть 4 типа ThreadPool, FixedThreadPool, CachedThreadPool, ScheduledThreadPool, SingleThreadedPool


        /*
        // FixedThreadPool
        // Java ExecutorService - Part 1 - Introduction      видос где все круто обьяснено
        // Он создает указанное кол-во потоков, потом загоняет все таски в thread-safe массив block-queue, и раздает
        // по одной задаче всем потокам, пока задачи не кончатся
        // "Я беру задачу и отдаю одному потоку, потом след задачу след потоку, если я выбрал задачу, а потоки еще заняты,
        // я жду, пока освободится один из потоков"
        ExecutorService service = Executors.newFixedThreadPool(numOfCores); // 500 операций 4 потока   45.268 ms
        for (int i = 0; i < numOfThreadsOrIterations; i++) {
            service.submit(new Runner(bank, numOfAccounts, start));
        }
        service.shutdown();
        */


        /*
        // CachedThreadPool
        // Java ExecutorService - Part 2 - Type of Pools
        // Он принимает все задачи, и по сути создает поток для каждой задачи, а через 60 сек убивает поток,
        // если он не получает задач (поэтому в параметр не принимает кол-во потоков)
        // Логику можно описать так:
        // "Я беру задачу и отдаю потоку, потом беру след задачу, и если поток еще занят, то я создаю новый поток
        // для этой задачи, если поток не получает задач в течении 60 сек, я его убиваю"
        ExecutorService service = Executors.newCachedThreadPool(); // 500 операций 47271 ms
        for (int i = 0; i < numOfThreadsOrIterations; i++) {
            service.submit(new Runner(bank, numOfAccounts, start));
        }
        service.shutdown();
        */


        /*
        // ScheduledThreadPool
        // Java ExecutorService - Part 2 - Type of Pools
        // По сути тот же самый FixedThreadPool, только здест можно задавать промежутки между тасками, будет ли поток
        // ждать ПЕРЕД выполнением задачи, или ПОСЛЕ выполнения, или просто будет выполнять её КАЖДЫЕ неск секунд
        ScheduledExecutorService service = Executors.newScheduledThreadPool(4);

        // schedule()
        for (int i = 0; i < numOfThreadsOrIterations; i++) {
            service.schedule(new Runner(bank, numOfAccounts, start), 2, TimeUnit.SECONDS);
            System.out.println("DONE");             // schedule() запускает поток и начнет таск после первых 2 сек ожидания и сделает таск 1 раз
        }
        */



        /*
        // scheduleAtFixedRate()
        // С одним таском и потоком
        service.scheduleAtFixedRate(new Runner(bank, numOfAccounts, start), 1, 2, TimeUnit.SECONDS);
        System.out.println("DONE");  // scheduleAtFixedRate() запускает поток который будет выполнять задачу бесконечно, с первичным ожиданием 1 сек, каждые 2 сек ПОСЛЕ НАЧАЛА пред таска
        //ИЛИ
        // Чтобы увидеть работу нескольких потоков и тасков
        service.scheduleAtFixedRate(new Runner(bank, numOfAccounts, start), 1, 2, TimeUnit.SECONDS);
        service.scheduleAtFixedRate(new Runner(bank, numOfAccounts, start), 1, 2, TimeUnit.SECONDS);
        service.scheduleAtFixedRate(new Runner(bank, numOfAccounts, start), 1, 2, TimeUnit.SECONDS);
        service.scheduleAtFixedRate(new Runner(bank, numOfAccounts, start), 1, 2, TimeUnit.SECONDS);
        System.out.println("DONE"); // Здесь видно как работают и распределяются потоки в таком варианте

        scanner.nextLine(); // Чтобы остановить поток нажать Enter
        service.shutdown();
        */



        /*
        // scheduledAtFixedDelay()
        // С одним таском и потоком
        service.scheduleWithFixedDelay(new Runner(bank, numOfAccounts, start), 1, 2, TimeUnit.SECONDS);
        System.out.println("DONE");  // ScheduledAtFixedDelay() запускает поток который будет выполнять задачу бесконечно, с первичным ожиданием 1 сек, ПОТОМ ЖДЕТ 2 сек ПОСЛЕ КОНЦА пред таска
        //ИЛИ
        // Чтобы увидеть работу нескольких потоков и тасков
        service.scheduleAtFixedRate(new Runner(bank, numOfAccounts, start), 1, 2, TimeUnit.SECONDS);
        service.scheduleAtFixedRate(new Runner(bank, numOfAccounts, start), 1, 2, TimeUnit.SECONDS);
        service.scheduleAtFixedRate(new Runner(bank, numOfAccounts, start), 1, 2, TimeUnit.SECONDS);
        service.scheduleAtFixedRate(new Runner(bank, numOfAccounts, start), 1, 2, TimeUnit.SECONDS);
        System.out.println("DONE"); // Здесь видно как работают и распределяются потоки в таком варианте

        scanner.nextLine(); // Чтобы остановить поток нажать Enter
        service.shutdown();
        */


        /*
        // SingleThreadExecutor
        // Java ExecutorService - Part 2 - Type of Pools
        // По сути просто запускает один поток, это как FixedThreadExecutor с параметром 1
        // Может пригодится если нам обязательно нужна последовательность выполнения задачи, которую не обеспечивают
        // другие пулы
        ExecutorService service = Executors.newSingleThreadExecutor(); // 500 операций 56449 ms
        for (int i = 0; i < numOfThreadsOrIterations; i++) {
            service.submit(new Runner(bank, numOfAccounts, start));
        }
        service.shutdown();
        */




        // ExecutorService создает "пул" из определенного количества потоков, которые потом распределяют по одной задаче на поток
        // автоматически
        // Если это CPU Intensive задача, то лучше делать кол-во потоков равное кол-ву процессоров в компе
        // Если это IO Intensive задача(работа с БД, или с HTML и тд) то лучше делать много потоков, тк иначе потоки будут
        // постоянно ждать Инпута от ресурсов, и в это время программа будет просто стоять тк процессоры заняты этим ожиданием
        // ОЧЕНЬ ВАЖНО! Если вылезает Exception, то потоки продолжают работать,
        //
        // "Java ExecutorService - Part 1 - Introduction" - отличный видос на эту тему










        // Для тестов

        //createAndStartThreads();                                          // 1.000 операций   157.701 ms

        /*long start = System.currentTimeMillis();
        Runner runner = new Runner(bank, numOfAccounts, start);             // 1.000 операций 1 main поток 43767 ms
        for (int i = 0; i < numOfThreadsOrIterations; i++) {
            runner.go();
        }*/

        scanner.nextLine();
        System.out.println("Баланс банка " + Bank.bankBalance);

        printAllAccountsBalance();

        printSumOfAllAccounts();
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

