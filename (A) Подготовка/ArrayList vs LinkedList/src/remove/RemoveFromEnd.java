package remove;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RemoveFromEnd {

    static int numOfElements = 10000000; // 10 mil

    public static void main(String[] args) {

        // ArrayList лучше

        List<Long> list = new ArrayList<>();
        for (long i = 0; i < numOfElements; i++) {
            list.add(i);
        }
        System.out.println("FILLED");

        int half = 5000000;
        long start = System.currentTimeMillis();
        for (int i = 0; i < half; i++) {
            list.remove(list.size() - 1);
        }
        System.out.println(System.currentTimeMillis() - start + " ms"); // 22 ms
        System.out.println(list.size());


        List<Long> linked = new LinkedList<>();
        for (long i = 0; i < numOfElements; i++) {
            linked.add(i);
        }
        System.out.println("FILLED");

        long start1 = System.currentTimeMillis();
        for (int i = 0; i < half; i++) {
            linked.remove(linked.size() - 1);
        }
        System.out.println(System.currentTimeMillis() - start1 + " ms"); // 176 ms
        System.out.println(linked.size());
    }
}
