public class Test {

    int x;

    public Test(int x) {
        this.x = x;
    }

    public void print(int x) {

        for (int i = 0; i < 1000000; i++) {
            x++;
        }
        System.out.println(x + " " + Thread.currentThread().getName());
    }

    public synchronized void inc() {

        for (int i = 0; i < 1000000; i++) {
            x++;
        }
        System.out.println(x + " " + Thread.currentThread().getName());
    }


}
