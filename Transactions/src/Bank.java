import java.util.Hashtable;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

public class Bank {

    private final Hashtable<String, Account> accounts = new Hashtable<>();
    private final Random random = new Random();
    static long bankBalance; // Количество денег во всем банке

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
    public synchronized void transfer(String fromAccountNum, String toAccountNum, long amount) {

        AtomicBoolean isBlocked = new AtomicBoolean(
                accounts.get(fromAccountNum).isBlocked() || accounts.get(toAccountNum).isBlocked());

        if (isBlocked.get()) {
            System.out.println("SOMEONE IS BLOCKED   " + Thread.currentThread().getName());
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return;
        }

        System.out.printf("Transfer request From: \"%s\", To: \"%s\", Amount: %d   %s%n",
                fromAccountNum, toAccountNum, amount, Thread.currentThread().getName());

        if (amount > 50000) {
            try {
                if (isFraud(fromAccountNum, toAccountNum, amount)) {
                    System.out.printf("USERS BLOCKED: \"%s\" AND \"%s\"   %s%n", fromAccountNum, toAccountNum,
                            Thread.currentThread().getName());
                    accounts.get(fromAccountNum).setBlocked(true);
                    accounts.get(toAccountNum).setBlocked(true);
                    wait();
                } else {
                    System.out.printf("USERS FREE: \"%s\" AND \"%s\"   %s%n", fromAccountNum, toAccountNum,
                            Thread.currentThread().getName());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        balanceLower(fromAccountNum, amount);
        balanceRaise(toAccountNum, amount);

        System.out.println("+++ From \"" + fromAccountNum + "\" balance: " + getBalance(fromAccountNum) +
                ", To \"" + toAccountNum + "\" balance: " + getBalance(toAccountNum) + "   "
                + Thread.currentThread().getName());
    }


    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */
    public AtomicLong getBalance(String accountNum) {

        return accounts.get(accountNum).getBalance();
    }

    public Hashtable<String, Account> getAccounts() {
        return accounts;
    }

    public void balanceRaise(String accountNum, long amount){

        accounts.get(accountNum).setBalance(
                new AtomicLong(getBalance(accountNum).longValue() - amount));
        bankBalance += amount;
    }

    public void balanceLower(String accountNum, long amount){

        accounts.get(accountNum).setBalance(
                new AtomicLong(getBalance(accountNum).longValue() + amount));
        bankBalance -= amount;
    }
}
