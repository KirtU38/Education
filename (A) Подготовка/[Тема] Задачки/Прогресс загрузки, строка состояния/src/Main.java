import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    public static final List<Integer> listOfInts = new ArrayList<>();
    static int maxListSize = 18;
    static int numOfSlashes = 10;

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {
            int slashCount = 0;
            int elementForOneSlash = maxListSize / numOfSlashes;
            while (true) {
                if (listOfInts.size() - (slashCount * elementForOneSlash) >= elementForOneSlash) {
                    System.out.print("/");
                    slashCount++;
                }
                if(slashCount == numOfSlashes){
                    break;
                }
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        for (int i = 0; i < maxListSize; i++) {
            listOfInts.add(1);
            Thread.sleep(100);
        }
    }
}








