import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicLong;

public class AccountsTester {

    static int numOfThreads = 10;
    static int iterationsForEachThread = 100;
    static AtomicLong accountStartingBalance = new AtomicLong(500000);
    static Random random = new Random();
    static Bank bank = new Bank();

    public static void main(String[] args) {

        bank.getAccounts().put("1", new Account("1", accountStartingBalance));
        bank.getAccounts().put("2", new Account("2", accountStartingBalance));


        ArrayList<Thread> threads = new ArrayList<>(); // Создаем и запускам потоки
        for (int i = 0; i < numOfThreads; i++) {
            threads.add(new Thread(AccountsTester::go));
        }
        threads.forEach(Thread::start);


        Scanner scanner = new Scanner(System.in); // Баланс банка выводится по клавише Enter
        scanner.nextLine();
        System.out.println("Конечный баланс 1 - " + bank.getBalance("1"));
        System.out.println("Конечный баланс 2 - " + bank.getBalance("2"));
        //System.out.println(bank.getBalance("1") + bank.getBalance("2"));
    }

    public static void go() {

        for (int i = 0; i < iterationsForEachThread; i++) {
            int randomAmount = random.nextInt(60000);  // 5% что сумма будет > 50.000
            bank.transfer("1","2", randomAmount);
            bank.transfer("2","1", randomAmount);
        }
    }
}
