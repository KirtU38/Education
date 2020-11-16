import java.util.HashMap;
import java.util.Random;

public class Bank {

    private final HashMap<String, Account> accounts = new HashMap<>();
    private final Random random = new Random();
    public static long bankBalance; // Количество денег во всем банке

    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
            throws InterruptedException {
        System.out.printf("CHECKING FRAUD FROM \"%s\" TO \"%s\" %d   %s%n", fromAccountNum, toAccountNum, amount, Thread.currentThread().getName());
        Thread.sleep(1000);
        return random.nextBoolean();
    }

    /**
     * TODO: реализовать метод. Метод переводит деньги между счетами.
     * Если сумма транзакции > 50000, то после совершения транзакции,
     * она отправляется на проверку Службе Безопасности – вызывается
     * метод isFraud. Если возвращается true, то делается блокировка
     * счетов (как – на ваше усмотрение)
     */
    public void transfer(String fromAccountNum, String toAccountNum, long amount) {

        System.out.printf("Запрос к \"%s\" И \"%s\"   %s%n",
                fromAccountNum, toAccountNum, Thread.currentThread().getName());

        // Если выпали одинаковые номера - выходим из метода
        if (fromAccountNum.equals(toAccountNum)) {
            System.out.printf("НЕЛЬЗЯ ПЕРЕВОДИТЬ НА ТОТ ЖЕ НОМЕР \"%s\"   %s%n", fromAccountNum,
                    Thread.currentThread().getName());
            return;
        }

        // Это тест чтобы понять поочередность потоков и тд для DeadLockTester

        /*synchronized (accounts.get(fromAccountNum)) {
            System.out.println("from \"" + fromAccountNum + "\" прошел дальше   "
                    + Thread.currentThread().getName());
            synchronized (accounts.get(toAccountNum)) {
                System.out.println("to \"" + toAccountNum + "\" прошел дальше   "
                        + Thread.currentThread().getName());*/


        synchronized (accounts.get(fromAccountNum).compareTo(accounts.get(toAccountNum))
                > 0 ? accounts.get(fromAccountNum) : accounts.get(toAccountNum)) {
            System.out.println("from \"" + fromAccountNum + "\"(" + toAccountNum + ") прошел дальше   "
                    + Thread.currentThread().getName());
            synchronized (accounts.get(fromAccountNum).compareTo(accounts.get(toAccountNum))
                    > 0 ? accounts.get(toAccountNum) : accounts.get(fromAccountNum)) {
                System.out.println("to (" + fromAccountNum + ")\"" + toAccountNum + "\" прошел дальше   "
                        + Thread.currentThread().getName());

                Account fromAcc = accounts.get(fromAccountNum);
                Account toAcc = accounts.get(toAccountNum);

                // Проверка на блокировку
                boolean isBlocked = fromAcc.isBlocked() || toAcc.isBlocked();

                // Если заблокирован, выйти из метода
                if (isBlocked) {
                    System.out.println("DENIED \"" + fromAccountNum + "\" OR \"" + toAccountNum + "\" IS BLOCKED   "
                            + Thread.currentThread().getName());
                    return;
                }

                System.out.printf("Transfer request From: \"%s\", To: \"%s\", Amount: %d   %s%n",
                        fromAccountNum, toAccountNum, amount, Thread.currentThread().getName());

                // Проверка на мошенничество
                if (amount > 50000) {
                    try {
                        if (isFraud(fromAccountNum, toAccountNum, amount)) {
                            fromAcc.setBlocked(true);
                            toAcc.setBlocked(true);
                            System.out.printf("USERS BLOCKED: \"%s\" AND \"%s\"   %s%n", fromAccountNum, toAccountNum,
                                    Thread.currentThread().getName());
                            return;

                        } else {
                            System.out.printf("USERS FREE: \"%s\" AND \"%s\"   %s%n", fromAccountNum, toAccountNum,
                                    Thread.currentThread().getName());
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // Перевод денег
                fromAcc.setBalance(getBalance(fromAccountNum) + amount);
                bankBalance -= amount;

                toAcc.setBalance(getBalance(toAccountNum) - amount);
                bankBalance += amount;

                System.out.println("+++ From \"" + fromAccountNum + "\" balance: " + getBalance(fromAccountNum) +
                        ", To \"" + toAccountNum + "\" Amount: " + amount + "   "
                        + Thread.currentThread().getName());
            }
        }
    }

    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */
    public long getBalance(String accountNum) {

        return accounts.get(accountNum).getBalance();
    }

    public HashMap<String, Account> getAccounts() {
        return accounts;
    }
}




