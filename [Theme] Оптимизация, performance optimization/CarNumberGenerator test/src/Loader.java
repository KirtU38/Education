import java.util.ArrayList;
import java.util.List;

public class Loader {

    // 1 регион
    public static final int BUFFER_SIZE = 15_536_448;

    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();
        char[] letters = {'Y', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
        List<Thread> listOfThreads = new ArrayList<>();

        for (int startRegion = 1, endRegion = 10; startRegion < 100; startRegion = endRegion, endRegion += 10) {
            listOfThreads.add(new Thread(new CarNumberGenerator(startRegion, endRegion, letters, BUFFER_SIZE)));
        }

        listOfThreads.forEach(Thread::start);
        for (Thread thread : listOfThreads) {
            thread.join();
        }

        System.out.println(System.currentTimeMillis() - start + " ms");
    }
}
