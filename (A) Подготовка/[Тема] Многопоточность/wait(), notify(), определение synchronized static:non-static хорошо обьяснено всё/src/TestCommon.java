public class TestCommon {

    static int i;
    static int j;

    // Наглядно показано как именно работают эти методы в классе TestCommonUnderstand
    public synchronized void print() throws InterruptedException {

        for (i = 0; i < 20; i++) {
            if ((i - j) >= 5) {
                notify();
                wait();
            }
            System.out.println("Live");
            Thread.sleep(200);
        }
        notifyAll();
    }

    public synchronized void wtf() throws InterruptedException {

        for (j = 0; j < 20; j++) {
            if ((j - i) == 0) {
                notify();
                wait();
            }
            System.out.println("WTF is goin on dude");
            Thread.sleep(200);
        }
        notifyAll();
    }
}
