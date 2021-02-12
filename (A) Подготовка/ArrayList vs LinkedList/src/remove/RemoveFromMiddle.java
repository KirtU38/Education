package remove;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RemoveFromMiddle {

    static int numOfElements = 100_000; // 10 тыс

    public static void main(String[] args) {

        // ArrayList намного быстрее (хотя оба супер медленные, всего 100 тыс элементов)

        int half = numOfElements / 2;

        List<Long> list = new ArrayList<>();
        for (long i = 0; i < numOfElements; i++) {
            list.add(i);
        }
        System.out.println("FILLED");

        long start = System.currentTimeMillis();
        for (int i = 0; i < half; i++) {
            list.remove(list.size() / 2);
        }
        System.out.println(System.currentTimeMillis() - start + " ms"); // 372 ms 10 тыс


        List<Long> linked = new LinkedList<>();
        for (long i = 0; i < numOfElements; i++) {
            linked.add(i);
        }
        System.out.println("FILLED");

        long start1 = System.currentTimeMillis();
        for (int i = 0; i < half; i++) {
            linked.remove(linked.size() / 2);
        }
        System.out.println(System.currentTimeMillis() - start1 + " ms"); // 5.706 ms 10 тыс
    }
}
