import java.util.Random;
import java.util.Scanner;

public class DeadLockTester {

    static int numOfThreads = 2;
    static int iterationsForEachThread = 1000;
    static long accountStartingBalance = 500000;
    static Random random = new Random();
    static Bank bank = new Bank();

    public static void main(String[] args) {

        bank.getAccounts().put("1", new Account("1", accountStartingBalance));
        bank.getAccounts().put("2", new Account("2", accountStartingBalance));
        bank.getAccounts().put("7", new Account("7", accountStartingBalance));

        new Thread(DeadLockTester::go).start();
        new Thread(DeadLockTester::go1).start();

        Scanner scanner = new Scanner(System.in); // Баланс банка выводится по клавише Enter
        scanner.nextLine();
        System.out.println("Конечный баланс 1 - " + bank.getBalance("1"));
        System.out.println("Конечный баланс 2 - " + bank.getBalance("2"));
    }

    public static void go() {

        for (int i = 0; i < iterationsForEachThread; i++) {
            int randomAmount = random.nextInt(40000);  // 5% что сумма будет > 50.000
            bank.transfer("1","2", randomAmount);
        }
        System.out.println("Закончил   " + Thread.currentThread().getName());
    }

    public static void go1() {

        for (int i = 0; i < iterationsForEachThread; i++) {
            int randomAmount = random.nextInt(40000);  // 5% что сумма будет > 50.000
            bank.transfer("7","2", randomAmount);
        }
        System.out.println("Закончил   " + Thread.currentThread().getName());
    }
}
