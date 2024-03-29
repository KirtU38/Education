package com;

import java.util.*;
import java.util.stream.IntStream;

public class FibWithArray {

    static int numOfNumbers = 60;
    static HashMap<Integer, Integer> list = new HashMap<>();

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        System.out.println(fib(numOfNumbers));
        System.out.println(System.currentTimeMillis() - start + " ms");
    }

    public static int fib(int number) {

        if (number <= 2) {
            list.put(number, 1);
            return 1;
        }

        int firstFib;
        int secondFib;

        if (list.containsKey(number - 1)) {
            firstFib = list.get(number - 1);
        } else {
            firstFib = fib(number - 1);
            list.put(number - 1, firstFib);
        }

        if (list.containsKey(number - 2)) {
            secondFib = list.get(number - 2);
        } else {
            secondFib = fib(number - 2);
            list.put(number - 2, secondFib);
        }

        return firstFib + secondFib;
    }
}


