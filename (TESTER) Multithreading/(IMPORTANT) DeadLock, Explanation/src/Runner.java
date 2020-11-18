import java.util.Random;

public class Runner implements Runnable {

    int iterationsForEachThread;
    int numOfAccounts;
    Random random = new Random();
    Bank bank;

    public Runner(int iterationsForEachThread, Bank bank, int numOfAccounts) {
        this.iterationsForEachThread = iterationsForEachThread;
        this.bank = bank;
        this.numOfAccounts = numOfAccounts;
    }

    @Override
    public void run() {

        for (int i = 0; i < iterationsForEachThread; i++) {
            String randomAccFrom = String.valueOf(random.nextInt(numOfAccounts));
            String randomAccTo = String.valueOf(random.nextInt(numOfAccounts));

            int randomAmount = random.nextInt(60000);  // 5% что сумма будет > 50.000
            bank.transfer(randomAccFrom, randomAccTo, randomAmount);

            /*} else {
                bank.getBalance(randomAccFrom);
                System.out.printf("ID: %s Balance: %d   %s%n", randomAccFrom, bank.getBalance(randomAccFrom), Thread.currentThread().getName());
            }*/
        }
        System.out.println("СДЕЛАЛ ВСЕ ОПЕРАЦИИ  " + Thread.currentThread().getName());
    }
}
