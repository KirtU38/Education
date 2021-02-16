import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();

        // WithSharedResource - здесь общим ресурсом является instance variable List<Integer> внутри класса
        WithSharedResource test = new WithSharedResource();
        Thread thread = new Thread(() -> {
            try {
                test.print(0, 10_000_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "first thread");
        Thread thread1 = new Thread(() -> {
            try {
                test.print(0, 10_000_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "second thread");

        // NoSharedResource - List<Integer> обьявлен внутри метода
        /*NoSharedResource test = new NoSharedResource();
        Thread thread = new Thread(() -> {
            try {
                test.print(0, 10_000_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "first thread");
        Thread thread1 = new Thread(() -> {
            try {
                test.print(0, 10_000_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "second thread");*/

        thread.start();
        thread1.start();
        thread.join();
        thread1.join();

        System.out.println(System.currentTimeMillis() - start + " ms");
    }
}
// Когда разными тредами вызывается один и тот же метод одного обьекта, то никакой проблемы нет, каждые тред имеет свою
// версию этого метода.
// Проблема возникает когда этот метод обращается к "общему" ресурсу, допустим как в нашем примере instance variable
// List<Integer> в классе WithSharedResource

// Это можно решить thread-safe коллекцией, например Vector(ArrayList, только thread-safe)

// 2074 ms 20mil ArrayList   - хз кажется Vector неплохо реализован
// 1992 ms 20mil Vector








