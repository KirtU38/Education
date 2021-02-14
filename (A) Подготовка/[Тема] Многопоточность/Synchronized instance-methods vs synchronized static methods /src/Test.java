public class Test {

    public static synchronized void print() throws InterruptedException {

        for (int i = 0; i < 10; i++) {
            System.out.println("Live");
            Thread.sleep(200);
        }
    }

    public synchronized void wtf() throws InterruptedException {

        for (int j = 0; j < 10; j++) {
            System.out.println("WTF is goin on dude");
            Thread.sleep(200);
        }
    }
}
