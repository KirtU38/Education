public class Account implements Comparable<Account> {

    private String accNumber;
    private long balance;
    private boolean isBlocked = false;

    public Account(String accNumber, long balance) {
        this.accNumber = accNumber;
        this.balance = balance;
    }

    public synchronized long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public synchronized boolean isBlocked() {
        return isBlocked;
    }

    public synchronized void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public String getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(String accNumber) {
        this.accNumber = accNumber;
    }

    @Override
    public int compareTo(Account account) {
        return this.accNumber.compareTo(account.accNumber);
    }
}
