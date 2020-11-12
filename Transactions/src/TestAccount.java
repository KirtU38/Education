import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class TestAccount implements Runnable {

    int iterationsForEachThread = 5;
    Random random = new Random();
    Bank bank;

    public TestAccount(Bank bank) {
        this.bank = bank;
    }

    @Override
    public void run() {

        go(bank);
    }

    public void go(Bank bank) {

        for (int i = 0; i < iterationsForEachThread; i++) {
            String from = Thread.currentThread().getName();
            AtomicBoolean isTransaction = new AtomicBoolean(random.nextBoolean()); // Транзакия или ппроверка баланса
            //boolean isTransaction = random.nextBoolean();
            if (isTransaction.get()) { // Трансфер
                int randomAmount = random.nextInt(60000);  // 5% что сумма будет > 50.000
                String randomAccTo = String.valueOf(random.nextInt(100));

                bank.transfer(from, randomAccTo, randomAmount);
            } else { // Баланс
                bank.getBalance(from);
                System.out.printf("ID: %s Balance: %d   %s%n", from, bank.getBalance(from).longValue(), Thread.currentThread().getName());
            }
        }
        System.out.println("СДЕЛАЛ ВСЕ ОПЕРАЦИИ  " + Thread.currentThread().getName());
    }
}
