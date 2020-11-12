import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

public class MainTester {

    static int numOfThreads = 100;
    static int iterationsForEachThread = 5;
    static AtomicLong accountStartingBalance = new AtomicLong(500000);
    static Random random = new Random();
    static Bank bank = new Bank();

    public static void main(String[] args) {

        fillBank();

        System.out.println(Bank.bankBalance);

        createAndStartThreads();

        Scanner scanner = new Scanner(System.in); // Баланс банка выводится по клавише Enter
        scanner.nextLine();
        System.out.println("Баланс банка " + Bank.bankBalance);

        printAllAccountsBalance();

        printSumOfAllAccounts();
    }

    /*public static void go() {

        for (int i = 0; i < iterationsForEachThread; i++) {

            String from = Thread.currentThread().getName();
            String randomAccTo = String.valueOf(random.nextInt(100));
            AtomicBoolean isTransaction = new AtomicBoolean(random.nextBoolean()); // Транзакия или ппроверка баланса
            if (isTransaction.get()) { // Трансфер
                int randomAmount = random.nextInt(60000);  // 5% что сумма будет > 50.000

                bank.transfer(from, randomAccTo, randomAmount);
            } else { // Баланс
                bank.getBalance(from);
                System.out.printf("ID: %s Balance: %d   %s%n", from, bank.getBalance(from).longValue(), Thread.currentThread().getName());
            }
        }
        System.out.println("СДЕЛАЛ ВСЕ ОПЕРАЦИИ  " + Thread.currentThread().getName());
    }*/

    public static void fillBank() {

        for (int i = 0; i < 100; i++) {  // Заполнить банк 100 аккаунтами с 500.000 на счету у каждого
            String accountId = String.valueOf(i);
            bank.getAccounts().put(accountId, new Account(accountId, accountStartingBalance));
            Bank.bankBalance += accountStartingBalance.longValue();
        }
    }

    public static void createAndStartThreads() {

        //ArrayList<Thread> threads = new ArrayList<>(); // Создаем и запускам потоки
        for (int i = 0; i < numOfThreads; i++) {
            TestAccount testAccount = new TestAccount(bank);
            new Thread(testAccount, String.valueOf(i)).start();
        }
        //threads.forEach(Thread::start);
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
            checkBankBalance = checkBankBalance + bank.getBalance(id).longValue();
            System.out.println(i + " Сложить все счета обратно " + checkBankBalance);
        }
    }
}

