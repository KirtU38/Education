public class TestCommonUnderstand {

    static int i;
    static int j;

    // Здесь наглядно показано как работают non-static synchronized методы
    public void print() throws InterruptedException {

        synchronized (this) {           // Захватываем монитор у Обьекта класса
            for (i = 0; i < 20; i++) {
                if ((i - j) >= 5) {
                    this.notify();
                    this.wait();
                }
                System.out.println("Live");
                Thread.sleep(200);
            }
            this.notifyAll();
        }
    }

    public void wtf() throws InterruptedException {

        synchronized (this) {
            for (j = 0; j < 20; j++) {
                if ((j - i) == 0) {
                    this.notify();
                    this.wait();
                }
                System.out.println("WTF is goin on dude");
                Thread.sleep(200);
            }
            this.notifyAll();
        }
    }
}
