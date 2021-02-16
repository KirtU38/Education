public class TestStatic {


    static int i;
    static int j;
    private static final Object dummy = new Object();
    static String fuck = "fuck";

    public static void print() throws InterruptedException {

        synchronized (dummy) {
            for (i = 0; i < 20; i++) {
                if ((i - j) >= 5) {
                    //fuck.notify(); - не сработает, тк мы захватываем монитор только у dummy(в synchronized блоке), да и wtf не ждет освобождения монитора от fuck
                    dummy.notify();
                    dummy.wait();
                }
                System.out.println("Live");
                Thread.sleep(200);
            }
            dummy.notifyAll();
        }
    }

    public static void wtf() throws InterruptedException {

        synchronized (dummy) {
            for (j = 0; j < 20; j++) {
                if ((j - i) == 0) {
                    dummy.notify();
                    dummy.wait();
                }
                System.out.println("WTF is goin on dude");
                Thread.sleep(200);
            }
            dummy.notifyAll();
        }
    }
}
