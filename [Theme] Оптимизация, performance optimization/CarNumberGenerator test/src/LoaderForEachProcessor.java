import java.util.ArrayList;
import java.util.List;

public class LoaderForEachProcessor {

    // 1 регион
    public static final int BUFFER_SIZE = 15_536_455;
    public static final int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();
    public static final int REGIONS_COUNT = 99;

    public static void main(String[] args) throws InterruptedException {

        int regionsForEachProcessor = REGIONS_COUNT / NUMBER_OF_CORES;

        long start = System.currentTimeMillis();
        char[] letters = {'Y', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
        List<Thread> listOfThreads = new ArrayList<>();

        for (int startRegion = 1, endRegion = regionsForEachProcessor; startRegion < 100;
             startRegion = endRegion, endRegion += regionsForEachProcessor) {
            listOfThreads.add(new Thread(new CarNumberGenerator(startRegion, endRegion, letters, BUFFER_SIZE)));
        }




        listOfThreads.forEach(Thread::start);
        for (Thread thread : listOfThreads) {
            thread.join();
        }

        System.out.println(System.currentTimeMillis() - start + " ms");
    }
}
