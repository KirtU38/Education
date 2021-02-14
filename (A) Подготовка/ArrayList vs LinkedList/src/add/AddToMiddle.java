package add;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AddToMiddle {

    static int numOfElements = 100000; // 100.000

    public static void main(String[] args) {

        // ArrayList намного быстрее

        List<Long> list = new ArrayList<>();
        long start = System.currentTimeMillis();
        for (long i = 0; i < numOfElements; i++) {
            list.add(list.size() / 2, i);
        }
        System.out.println(System.currentTimeMillis() - start + " ms"); // 511 ms 100.000
        System.out.println(list.size());


        LinkedList<Long> linked = new LinkedList<>();
        long start1 = System.currentTimeMillis();
        for (long i = 0; i < numOfElements; i++) {
            linked.add(linked.size() / 2, i);
        }
        System.out.println(System.currentTimeMillis() - start1 + " ms"); // 14.902 ms 100.000
        System.out.println(linked.size());

    }
}
