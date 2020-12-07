package add;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AddToStart {

    static int numOfElements = 100000; // 100.000

    public static void main(String[] args) {

        // LinkedList быстрее

        List<Long> list = new ArrayList<>();

        long start = System.currentTimeMillis();
        for (long i = 0; i < numOfElements; i++) {
            list.add(0, i);
        }
        System.out.println(System.currentTimeMillis() - start + " ms"); // 1247 ms 100.000
        System.out.println(list.size());


        List<Long> linked = new LinkedList<>();

        long start1 = System.currentTimeMillis();
        for (long i = 0; i < numOfElements; i++) {
            linked.add(0, i);
        }
        System.out.println(System.currentTimeMillis() - start1 + " ms"); // 27 ms 100.000
        System.out.println(linked.size());

    }
}
