import java.util.ArrayList;
import java.util.List;

public class OneVsManyObjects {

    public static void main(String[] args) throws InterruptedException {

        // По скорости равны
        // 1000ms one object
        // 1000ms many objects
        long start = System.currentTimeMillis();
        List<Thread> listOfThreads = new ArrayList<>();

        // Many Threads-Many Objects
        /*for (int i = 0; i < 10; i++) {
            listOfThreads.add(new Thread(() -> {
                try {
                    new NoSharedResource().print(0, 1_000_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }));
        }*/

        // Many threads-One Object
        NoSharedResource test = new NoSharedResource();
        for (int i = 0; i < 10; i++) {
            listOfThreads.add(new Thread(() -> {
                try {
                    test.print(0, 1_000_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }));
        }




        listOfThreads.forEach(Thread::start);
        for (Thread thread : listOfThreads) {
            thread.join();
        }
        System.out.println(System.currentTimeMillis() - start + " ms");
    }
}









