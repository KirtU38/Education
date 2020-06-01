package com.company;

import java.text.DecimalFormat;
import java.util.*;

public class Main {

    static long start;
    static double duration;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        List<String> list = new ArrayList<>();

        String letters = "ABCEHKMOPTXY";
        DecimalFormat numberFormatter = new DecimalFormat("000");
        DecimalFormat regionFormatter = new DecimalFormat("00");


        for (int i = 0; i < letters.length(); i++) {
            String char1st = Character.toString(letters.charAt(i));
            for (int j = 0; j < 1000; j += 111) {
                String digits = numberFormatter.format(j);
                for (int k = 0; k < letters.length(); k++) {
                    String char2nd = Character.toString(letters.charAt(k));
                    for (int l = 0; l < letters.length(); l++) {
                        String char3rd = Character.toString(letters.charAt(l));
                        for (int m = 1; m < 200; m++) {
                            String regions = regionFormatter.format(m);
                            String finalNumber = char1st + digits + char2nd + char3rd + regions;
                            list.add(finalNumber);
                        }
                    }
                }

            }
        }

        Set<String> hashSet = new HashSet<>(list);
        Set<String> treeSet = new TreeSet<>(list);

        String input = scanner.nextLine().trim().toUpperCase();
        boolean containsInput;

        start = System.nanoTime();
        containsInput = list.contains(input);
        duration = (double) (System.nanoTime() - start) / 1e+6;
        System.out.printf("Поиск перебором: " + (containsInput ? "номер найден" : "номер не найден") +
                ", поиск занял %.2f ms%n", duration);


        Collections.sort(list);


        start = System.nanoTime();
        containsInput = (Collections.binarySearch(list, input) >= 0);
        duration = (double) (System.nanoTime() - start) / 1e+6;
        System.out.printf("Бинарный перебором: " + (containsInput ? "номер найден" : "номер не найден") +
                ", поиск занял %.2f ms%n", duration);


        start = System.nanoTime();
        containsInput = hashSet.contains(input);
        duration = (double) (System.nanoTime() - start) / 1e+6;
        System.out.printf("Поиск в HashSet: " + (containsInput ? "номер найден" : "номер не найден") +
                ", поиск занял %.2f ms%n", duration);


        start = System.nanoTime();
        containsInput = treeSet.contains(input);
        duration = (double) (System.nanoTime() - start) / 1e+6;
        System.out.printf("Поиск в TreeSet: " + (containsInput ? "номер найден" : "номер не найден") +
                ", поиск занял %.2f ms%n", duration);
    }
}

