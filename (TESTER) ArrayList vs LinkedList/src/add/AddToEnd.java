package add;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AddToEnd {

    static int numOfElements = 10000000; // 10 mil

    public static void main(String[] args) {

        // ArrayList быстрее

        List<Long> list = new ArrayList<>();

        long start = System.currentTimeMillis();
        for (long i = 0; i < numOfElements; i++) {
            list.add(i);
        }
        System.out.println(System.currentTimeMillis() - start + " ms"); // 1100 ms 10 mil
        System.out.println(list.size());


        List<Long> linked = new LinkedList<>();

        long start1 = System.currentTimeMillis();
        for (long i = 0; i < numOfElements; i++) {
            linked.add(i);
        }
        System.out.println(System.currentTimeMillis() - start1 + " ms"); // 2200 ms 10 mil
        System.out.println(linked.size());


        /*HashSet<Long> set = new HashSet<>();

        long start2 = System.currentTimeMillis();
        for (long i = 0; i < numOfElements; i++) {
            set.add(i);
        }
        System.out.println(System.currentTimeMillis() - start2 + " ms"); // 2300 ms 10 mil
        System.out.println(set.size());*/

    }
}
