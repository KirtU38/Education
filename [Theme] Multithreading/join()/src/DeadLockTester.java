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
    }

    public static void go() {

        for (int i = 0; i < iterationsForEachThread; i++) {
            int randomAmount = random.nextInt(40000);  // 5% что сумма будет > 50.000
            Account fromAccount = bank.getAccounts().get("1");
            Account toAccount = bank.getAccounts().get("2");

            bank.transfer(fromAccount,toAccount, randomAmount);
        }
        System.out.println("Закончил   " + Thread.currentThread().getName());
    }

    public static void go1() {

        for (int i = 0; i < iterationsForEachThread; i++) {
            int randomAmount = random.nextInt(40000);  // 5% что сумма будет > 50.000
            Account fromAccount = bank.getAccounts().get("7");
            Account toAccount = bank.getAccounts().get("2");

            bank.transfer(fromAccount,toAccount, randomAmount);
        }
        System.out.println("Закончил   " + Thread.currentThread().getName());
    }
}
