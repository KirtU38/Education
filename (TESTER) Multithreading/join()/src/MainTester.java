import java.util.ArrayList;
import java.util.Scanner;

public class MainTester {                         // Обьяснение в конце

    static int numOfThreads = 10;
    static int iterationsForEachThread = 10;
    static int numOfAccounts = 10;
    static long accountStartingBalance = 500000;
    static Bank bank = new Bank();

    public static void main(String[] args) {

        fillBankWithAccounts();

        System.out.println(Bank.bankBalance);

        createAndStartThreads();

        Scanner scanner = new Scanner(System.in); // Баланс банка выводится по клавише Enter
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
        for (int i = 0; i < numOfThreads; i++) {
            threads.add(new Thread(new Runner(iterationsForEachThread, bank, numOfAccounts)));
        }

        threads.forEach(t -> {
            t.start();
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // t.join() - означает "Сначала закончится этот поток, только потом начнется следующий"
        // или
        // "Ни один поток не может начать работу, пока не закончит работу поток, вызвавщий метод join()"

    }

    public static void printAllAccountsBalance() {

        for (int i = 0; i < bank.getAccounts().size(); i++) { // Вывести все счета

            String id = String.valueOf(i);
            Account account = bank.getAccounts().get(id);
            System.out.println("\"" + id + "\"" + " " + bank.getBalance(account));
        }
    }

    public static void printSumOfAllAccounts() {

        long checkBankBalance = 0;
        for (int i = 0; i < bank.getAccounts().size(); i++) { // Сложить все счета заново, должна получиться начальная сумма банка

            String id = String.valueOf(i);
            Account account = bank.getAccounts().get(id);
            checkBankBalance = checkBankBalance + bank.getBalance(account);
            System.out.println(i + " Сложить все счета обратно " + checkBankBalance);
        }
    }
}
