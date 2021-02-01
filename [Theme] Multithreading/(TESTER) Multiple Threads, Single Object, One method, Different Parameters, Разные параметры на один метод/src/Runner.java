public class Runner implements Runnable {

    Test test;
    int x;

    public Runner(Test test, int x) {
        this.test = test;
        this.x = x;
    }

    @Override
    public void run() {

        test.print(x);
        //test.inc();
    }
}
