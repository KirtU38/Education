public class TestStaticAndNonStatic {


    static int i;
    static int j;

    public void print() throws InterruptedException {

        synchronized (TestStaticAndNonStatic.class) {
            for (i = 0; i < 20; i++) {
                if ((i - j) >= 5) {
                    TestStaticAndNonStatic.class.notify();
                    TestStaticAndNonStatic.class.wait();
                }
                System.out.println("Live");
                Thread.sleep(200);
            }
            TestStaticAndNonStatic.class.notifyAll();
        }
    }

    public static synchronized void wtf() throws InterruptedException {


        for (j = 0; j < 20; j++) {
            if ((j - i) == 0) {
                TestStaticAndNonStatic.class.notify();
                TestStaticAndNonStatic.class.wait();
            }
            System.out.println("WTF is goin on dude");
            Thread.sleep(200);
        }
        TestStaticAndNonStatic.class.notifyAll();
    }
}
