public class Test {


    static int i;
    static int j;
    private static final Object lockLive = new Object();

    public static void print() throws InterruptedException {

        synchronized (lockLive) {
            for (i = 0; i < 50; i++) {
                if ((i - j) >= 5) {
                    lockLive.notify();
                    lockLive.wait();
                }
                System.out.println("Live");
                Thread.sleep(200);
            }
            lockLive.notifyAll();
        }
    }

    public static void wtf() throws InterruptedException {

        synchronized (lockLive) {
            for (j = 0; j < 50; j++) {
                if ((j - i) == 0) {
                    lockLive.notify();
                    lockLive.wait();
                }
                System.out.println("WTF is goin on dude");
                Thread.sleep(200);
            }
        }
    }
}
