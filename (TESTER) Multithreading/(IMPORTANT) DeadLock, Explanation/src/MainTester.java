import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class MainTester {                         // Обьяснение в конце

    static int numOfThreads = 10;
    static int iterationsForEachThread = 50;
    static long accountStartingBalance = 500000;
    static Random random = new Random();
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

    public static void go() {

        for (int i = 0; i < iterationsForEachThread; i++) {
            String randomAccFrom = String.valueOf(random.nextInt(100));
            String randomAccTo = String.valueOf(random.nextInt(100));
            boolean isTransaction = random.nextBoolean(); // Транзакия или проверка баланса

            //if (isTransaction) {
            int randomAmount = random.nextInt(60000);  // 5% что сумма будет > 50.000
            bank.transfer(randomAccFrom, randomAccTo, randomAmount);

            /*} else {
                bank.getBalance(randomAccFrom);
                System.out.printf("ID: %s Balance: %d   %s%n", randomAccFrom, bank.getBalance(randomAccFrom), Thread.currentThread().getName());
            }*/
        }
        System.out.println("СДЕЛАЛ ВСЕ ОПЕРАЦИИ  " + Thread.currentThread().getName());
    }

    public static void fillBankWithAccounts() {

        for (int i = 0; i < 100; i++) {  // Заполнить банк 100 аккаунтами с 500.000 на счету у каждого
            String accountId = String.valueOf(i);
            bank.getAccounts().put(accountId, new Account(accountId, accountStartingBalance));
            Bank.bankBalance += accountStartingBalance;
        }
    }

    public static void createAndStartThreads() {

        ArrayList<Thread> threads = new ArrayList<>(); // Создаем и запускам потоки
        for (int i = 0; i < numOfThreads; i++) {
            threads.add(new Thread(MainTester::go));
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
// Возьмем ситуацию связки fromAcc = "1" и toAcc = "2"
// Блок выглядит так - synchronized (accounts.get(fromAccountNum)) {     = "1"
//                         synchronized (accounts.get(toAccountNum)) {   = "2"
//
// Здесь самое важное что toAcc находится ВНУТРИ fromAcc, а значит 2 блокируется только в связке если первый акк был "1"
// Этот код говорит нам "1й аккаунт может использоваться только одним потоком, а 2й аккаунт может использоваться
// только одним потоком если первый параметр = 1(так как он ВНУТРИ первого synchronized блока)"
// То есть 2 аккаунт может зайти как первый параметр fromAcc, а 1 аккаунт зайти как второй toAcc
//
// lock - лочить этот обьект
// BLOCKED - обьект кем-то заблокирован
// wait - переход в режим ожидания (ожидание окончания потока, который исп-ет этот обьект)
//
//
// Ситуация где toAcc совпал
//
//                         from       to     |  from       to     |
//                                           |                    |
//                          1    ->   2      |   1    ->   2      |
//                         lock      lock    |  done      done    |
//                          7    ->   2      |   7    ->   2      |
//                         lock    BLOCKED   |  lock      lock    |
//                                  wait     |                    |
//
// Приходят 1 и 2 и говорят "Последовательная связка 1й аккаунт -> 2й аккаунт могут использоваться только одним потоком,
// блокирую аккаунты 1 и 2. Приходит 7->2. 7й аккаунт заходит по первому параметру, т.к нет выполняющихся потоков
// с первым параметром fromAcc = "7", а 2й акк проходит потому что нигде не выполняется поток со связкой 7->2,
// но 2й аккаунт залочен потоком который выполняет связку 1->2, поэтому 7->2 ждет выполнения связки 1->2 для снятия
// лока со 2 аккаунта, и потом спокойно выполняется"
//
//
//
// Ситуация где fromAcc совпал
//
//                         from       to     |  from       to     |
//                                           |                    |
//                          1    ->   2      |   1    ->   2      |
//                         lock      lock    |  done      done    |
//                          1    ->   7      |   1    ->   7      |
//                       BLOCKED     wait    |  lock      lock    |
//                         wait              |                    |
//
// Приходят 1 и 2 и говорят "Последовательная связка 1й аккаунт -> 2й аккаунт могут использоваться только одним потоком,
// блокирую аккаунты 1 и 2. Приходит 7->2. 7й аккаунт сразу встает на ожидание, т.к по первому параметру он не проходит,
// связка 1->2 заняла и заблочила 1й аккаунт, и 1->7 (7й аккаунт не лочится, т.к до него даже не дошел код)
// полной связкой встают в ожидание, и после завершения 1->2 начинают выполняться"
//
//
//
//
// Ситуация где fromAcc и toAcc совпали в разные стороны
//
//                         from       to     |  from       to     |
//                                           |                    |
//                          1    ->   2      |   1    ->   2      |
//                         lock      lock    |  wait      wait    |
//                          2    ->   1      |   2    ->   1      |
//                         lock      lock    |  wait      wait    |
//                                           |                    |
//
// Приходят 1->2 и спокойно лочат 1й и 2й аккаунт, параллельно приходит 2 на fromAcc и проходит, т.к по первому блоку
// synchronized он проходит (ПЕРВЫЙ блок говорит только то, что "Только один поток может выполнять код с 1м аккаунтом"
// он не говорит, что в этот блок не может войти 2й аккаунт, даже если он залочен как 2й параметр в предыдущем
// параллельном потоке), и также 1 прозодит во ВТОРОЙ блок, хотя он залочен, и Дэдлок происходит ТОЛЬКО ЕСЛИ ОБА ПОТОКА
// СДЕЛАЛИ ЗАПРОС ОДНОВРЕМЕННО, обе связки залочены и бесконечно ждут
//
//
//
// Что проихсодит когда мы справляемся с Deadlock методом compareTo
//
//
//
//                         from       to     |  from       to     |
//                                           |                    |  - ситуация
//                          1    ->   2      |   1    ->   2      |
//                         lock      lock    |  wait      wait    |
//                          2    ->   1      |   2    ->   1      |
//                         lock      lock    |  wait      wait    |
//                                           |                    |
//
// У нас Дэдлок, и мы у одной из этих "связок" меняем местами зависимость "верхний-нижний блок synchronized"
// До этого у 2->1 была логика "Только один поток может иметь доступ к 2у аккаунта, а потом к 1у(связке)"
// А стало "Только один поток имеет доступ к 1у аккаунту, а потом к 1у"
// И т.к 1й акк уже залочен первой связкой 1->2, то наш новый 1->1 просто начинает ожидание по первому параметру
//
//
//        synchronized (accounts.get(fromAccountNum).compareTo(accounts.get(toAccountNum))
//                > 0 ? accounts.get(fromAccountNum) : accounts.get(toAccountNum)) {
//            synchronized (accounts.get(fromAccountNum).compareTo(accounts.get(toAccountNum))
//                    > 0 ? accounts.get(toAccountNum) : accounts.get(fromAccountNum)) {
//
//
//                         from       to     |  from       to     |
//                                           |                    |
//                          1    ->   2      |   1    ->   2      |
//                         lock      lock    |  done      done    |
//                              \
//                                \
//                                  \
//                          2    ->   1      |   2    ->   1      |
//                         lock      lock    |  wait      wait    |
//                                           |                    |
//                         превращается в:
//
//                          1    ->    2
//                         wait       wait
//
// По сути задача просто превращать зеркальные связки в односторонние:
//
// 8->2                    2->8
// 5->1   превращаются в   5->1   и обрабатываются по правилу первого synchronized блока
// 7->3                    7->3
// 9->6                    9->6
//