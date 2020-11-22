import java.util.HashMap;
import java.util.Random;

public class Bank {

    private final HashMap<String, Account> accounts = new HashMap<>();
    private final Random random = new Random();
    public static long bankBalance;              // Количество денег во всем банке

    public synchronized boolean isFraud(Account fromAccountNum, Account toAccountNum, long amount)
            throws InterruptedException {
        System.out.printf("CHECKING FRAUD FROM \"%s\" TO \"%s\" %d   %s%n", fromAccountNum, toAccountNum, amount, Thread.currentThread().getName());
        Thread.sleep(1000);
        return random.nextBoolean();
    }





















    public void transfer(Account fromAccount, Account toAccount, long amount) {

        System.out.printf("Запрос к \"%s\" И \"%s\"   %s%n",
                fromAccount, toAccount, Thread.currentThread().getName());

        if (areSameAccount(fromAccount, toAccount) || someoneIsBlocked(fromAccount, toAccount)) {
            return;
        }

        // Это тест чтобы понять поочередность потоков и тд для DeadLockTester

        /*synchronized (accounts.get(fromAccount)) {
            System.out.println("from \"" + fromAccount + "\" прошел дальше   "
                    + Thread.currentThread().getName());
            synchronized (accounts.get(toAccount)) {
                System.out.println("to \"" + toAccount + "\" прошел дальше   "
                        + Thread.currentThread().getName());*/

        // БЛОК СИНХРОНИЗАЦИИ
        synchronized (fromAccount.compareTo(toAccount) > 0 ? fromAccount : toAccount) {

            System.out.println("from \"" + fromAccount + "\"(" + toAccount + ") прошел дальше   "
                    + Thread.currentThread().getName());

            synchronized (fromAccount.compareTo(toAccount) > 0 ? toAccount : fromAccount) {

                System.out.println("to (" + fromAccount + ")\"" + toAccount + "\" прошел дальше   "
                        + Thread.currentThread().getName());

                System.out.printf("Transfer request From: \"%s\", To: \"%s\", Amount: %d   %s%n",
                        fromAccount, toAccount, amount, Thread.currentThread().getName());

                // Проверка на мошенничество
                if (!wentThroughSecurity(fromAccount, toAccount, amount)) {
                    return;
                }

                // Перевод денег
                fromAccount.setBalance(getBalance(fromAccount) + amount);
                bankBalance -= amount;

                toAccount.setBalance(getBalance(toAccount) - amount);
                bankBalance += amount;

                System.out.println("+++ From \"" + fromAccount + "\" balance: " + getBalance(fromAccount) +
                        ", To \"" + toAccount + "\" Amount: " + amount + "   "
                        + Thread.currentThread().getName());
            }
        }
    }

    private boolean wentThroughSecurity(Account fromAccount, Account toAccount, long amount) {
        if (amount > 50000) {
            try {
                if (isFraud(fromAccount, toAccount, amount)) {
                    blockAccounts(fromAccount, toAccount);
                    return false;
                }
                System.out.printf("USERS FREE: \"%s\" AND \"%s\"   %s%n", fromAccount, toAccount,
                        Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    private void blockAccounts(Account fromAccount, Account toAccount) {
        fromAccount.setBlocked(true);
        toAccount.setBlocked(true);
        System.out.printf("USERS BLOCKED: \"%s\" AND \"%s\"   %s%n", fromAccount, toAccount,
                Thread.currentThread().getName());
    }

    private boolean someoneIsBlocked(Account fromAccount, Account toAccount) {
        if (fromAccount.isBlocked() || toAccount.isBlocked()) {
            System.out.println("DENIED \"" + fromAccount + "\" OR \"" + toAccount + "\" IS BLOCKED   "
                    + Thread.currentThread().getName());
            return true;
        }
        return false;
    }

    private boolean areSameAccount(Account fromAccount, Account toAccount) {
        if (fromAccount.getAccNumber().equals(toAccount.getAccNumber())) {
            System.out.printf("НЕЛЬЗЯ ПЕРЕВОДИТЬ НА ТОТ ЖЕ НОМЕР \"%s\"   %s%n", fromAccount,
                    Thread.currentThread().getName());
            return true;
        }
        return false;
    }

    public long getBalance(Account account) {

        return account.getBalance();
    }

    public HashMap<String, Account> getAccounts() {
        return accounts;
    }
}




