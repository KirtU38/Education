public class TestBlocks {


    static int i;
    static int j;
    private static final Object dummy = new Object();
    static String fuck = "fuck";

    public void print() throws InterruptedException {

        synchronized (dummy) {               // захватываем монитор и dummy и fuck, но освобождаем только fuck
            synchronized (fuck) {
                for (i = 0; i < 20; i++) {
                    if ((i - j) >= 5) {
                        //fuck.notify(); - не сработает, тк мы захватываем монитор только у dummy(в synchronized блоке)
                        fuck.notify();
                        fuck.wait(); // здесь мы освободили монитор обьекта fuck, но не обькета dummy, которого ждет метод wtf
                    }
                    System.out.println("Live");
                    Thread.sleep(200);
                }
                fuck.notifyAll();
            }
        }
    }

    public void wtf() throws InterruptedException {

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
