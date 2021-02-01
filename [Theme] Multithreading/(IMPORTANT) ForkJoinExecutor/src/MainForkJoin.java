import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.*;

public class MainForkJoin {                         // Обьяснение в конце

    static int numOfThreadsOrIterations = 10;     // Переменная показывает сразу для обоих случаев кол-во операций
    static int numOfAccounts = 100;
    static int numOfCores = Runtime.getRuntime().availableProcessors();
    static long accountStartingBalance = 500000;
    static Bank bank = new Bank();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        fillBankWithAccounts();

        long start = System.currentTimeMillis();



        // ForkJoinPool
        // Урок по Java 89: Многопоточность 24: ForkJoinFramework     - пока только так, больше оно нам не понадобилось
        // Используется для рекурсивных больших задач, котоые можно поделить на маленькие
        //

        ForkJoinPool fork = new ForkJoinPool(4);



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

