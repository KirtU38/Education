package get;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GetFromEnd {

    static int numOfElements = 10000000; // 10 mil

    public static void main(String[] args) {

        // Равны

        List<Long> list = new ArrayList<>();

        for (long i = 0; i < numOfElements; i++) {
            list.add(i);
        }
        System.out.println("FILLED");

        long start = System.currentTimeMillis();
        for (int i = 0; i < numOfElements; i++) {
            list.get(list.size() - 1);
        }
        System.out.println(System.currentTimeMillis() - start + " ms"); // 6 ms 10 mil



        List<Long> linked = new LinkedList<>();

        for (long i = 0; i < numOfElements; i++) {
            linked.add(i);
        }
        System.out.println("FILLED");

        long start1 = System.currentTimeMillis();
        for (int i = 0; i < numOfElements; i++) {
            linked.get(linked.size() - 1);
        }
        System.out.println(System.currentTimeMillis() - start1 + " ms"); // 6 ms 10 mil
    }
}
