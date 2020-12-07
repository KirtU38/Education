package get;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GetFromMiddle {

    static int numOfElements = 100000; // 100.000

    public static void main(String[] args) {

        // ArrayList НАМНОГО быстрее

        List<Long> list = new ArrayList<>();

        for (long i = 0; i < numOfElements; i++) {
            list.add(i);
        }
        System.out.println("FILLED");

        long start = System.currentTimeMillis();
        for (int i = 0; i < numOfElements; i++) {
            list.get(list.size() / 2);
        }
        System.out.println(System.currentTimeMillis() - start + " ms"); // 4 ms 100.000


        List<Long> linked = new LinkedList<>();

        for (long i = 0; i < numOfElements; i++) {
            linked.add(i);
        }
        System.out.println("FILLED");

        long start1 = System.currentTimeMillis();
        for (int i = 0; i < numOfElements; i++) {
            linked.get(linked.size() / 2);
        }
        System.out.println(System.currentTimeMillis() - start1 + " ms"); // 15.763 ms 100.000
    }
}
