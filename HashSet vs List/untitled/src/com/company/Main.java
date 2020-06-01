package com.company;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        
        long start;
        long end;

        List<String> list = new ArrayList<>();
        HashSet<String> set = new HashSet<>();


        for (int i = 0; i < 1000000; i++) {                   // 320 ms
            list.add("Элемент " + i);
        }


        for (int i = 0; i < 1000000; i++) {                   // 600 ms
            set.add("Элемент " + i);
        }


        System.out.println(list.contains("Элемент 999999"));  // 50 ms


        start = System.currentTimeMillis();
        System.out.println(set.contains("Элемент 999999"));   // 0 ms
        end = System.currentTimeMillis();
        System.out.println(end - start + " ms");
    }
}

