import java.util.Random;

public class Runner implements Runnable{

    int numOfAccounts;
    long start;
    Random random = new Random();
    Bank bank;

    public Runner(Bank bank, int numOfAccounts, long start) {
        this.bank = bank;
        this.numOfAccounts = numOfAccounts;
        this.start = start;
    }

    @Override
    public void run() {
        String randomAccFrom = String.valueOf(random.nextInt(numOfAccounts));
        String randomAccTo = String.valueOf(random.nextInt(numOfAccounts));

        int randomAmount = random.nextInt(60000);  // 5% что сумма будет > 50.000
        bank.transfer(randomAccFrom, randomAccTo, randomAmount);

        System.out.println("СДЕЛАЛ ВСЕ ОПЕРАЦИИ  " + Thread.currentThread().getName());
        System.out.println(System.currentTimeMillis() - start + " ms");
    }
}
