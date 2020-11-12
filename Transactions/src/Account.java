import java.util.concurrent.atomic.AtomicLong;

public class Account {

    private String accNumber;
    private AtomicLong balance;
    private boolean isBlocked = false;

    public Account(String accNumber, AtomicLong balance) {
        this.accNumber = accNumber;
        this.balance = balance;
    }

    public AtomicLong getBalance() {
        return balance;
    }

    public void setBalance(AtomicLong balance) {
        this.balance = balance;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }
}
