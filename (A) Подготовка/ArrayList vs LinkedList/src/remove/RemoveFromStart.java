package remove;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RemoveFromStart {

    static int numOfElements = 100000; // 100 тыс

    public static void main(String[] args) {

        // LinkedList НАМНОГО быстрее

        int half = numOfElements / 2;
        List<Long> list = new ArrayList<>();
        for (long i = 0; i < numOfElements; i++) {
            list.add(i);
        }
        System.out.println("FILLED");

        long start = System.currentTimeMillis();
        for (int i = 0; i < half; i++) {
            list.remove(0);
        }
        System.out.println(System.currentTimeMillis() - start + " ms"); // 1166 ms 100 тыс


        LinkedList<Long> linked = new LinkedList<>();
        for (long i = 0; i < numOfElements; i++) {
            linked.add(i);
        }
        System.out.println("FILLED");

        long start1 = System.currentTimeMillis();
        for (int i = 0; i < half; i++) {
            linked.removeFirst();
        }
        System.out.println(System.currentTimeMillis() - start1 + " ms"); // 3 ms 100 тыс
    }
}
