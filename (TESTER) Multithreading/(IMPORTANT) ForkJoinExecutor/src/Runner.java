import java.util.Random;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class Runner extends RecursiveTask<Integer> {

    int numOfAccounts;
    long start;
    Random random = new Random();
    Bank bank;

    public Runner(Bank bank, int numOfAccounts, long start) {
        this.bank = bank;
        this.numOfAccounts = numOfAccounts;
        this.start = start;
    }

    public void run() {
        String randomAccFrom = String.valueOf(random.nextInt(numOfAccounts));
        String randomAccTo = String.valueOf(random.nextInt(numOfAccounts));

        int randomAmount = random.nextInt(60000);  // 5% что сумма будет > 50.000
        bank.transfer(randomAccFrom, randomAccTo, randomAmount);

        System.out.println("СДЕЛАЛ ВСЕ ОПЕРАЦИИ  " + Thread.currentThread().getName());
        System.out.println(System.currentTimeMillis() - start + " ms");
    }

    @Override
    protected Integer compute() {
        return null;
    }
}
