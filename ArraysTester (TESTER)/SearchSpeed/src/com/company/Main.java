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

        //RANDOM NUMBER
        int[] numbersArr = new int[]{0, 111, 222, 333, 444, 555, 666, 777, 888, 999};

        int random1stLetter = (int) Math.round(Math.random() * 11);
        String letter1st = Character.toString(letters.charAt(random1stLetter));

        int randomNumber = (int) Math.round(Math.random() * 9);
        String numbers = numberFormatter.format(numbersArr[randomNumber]);

        int random2ndLetter = (int) Math.round(Math.random() * 11);
        String letter2nd = Character.toString(letters.charAt(random2ndLetter));

        int random3rdLetter = (int) Math.round(Math.random() * 11);
        String letter3rd = Character.toString(letters.charAt(random3rdLetter));

        int randomRegion = (int) Math.round(Math.random() * 198);
        String region = regionFormatter.format(randomRegion);

        String input = letter1st + numbers + letter2nd + letter3rd + region;
        //////////////////


        List<String> linkedList = new LinkedList<>(list);
        Set<String> hashSet = new HashSet<>(list);
        Set<String> treeSet = new TreeSet<>(list);

        String[] array = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }

        boolean containsInput = false;


        // ТЕСТЫ
        start = System.nanoTime();
        for (String item : array) {
            if (item.equals(input)) {
                containsInput = true;
                break;
            }
        }
        duration = (double) (System.nanoTime() - start) / 1e+6;
        System.out.printf("Поиск перебором String[]: " + (containsInput ? "номер найден" : "номер не найден") +
                ", поиск занял %.2f ms%n", duration);


        start = System.nanoTime();
        containsInput = list.contains(input);
        duration = (double) (System.nanoTime() - start) / 1e+6;
        System.out.printf("Поиск перебором List: " + (containsInput ? "номер найден" : "номер не найден") +
                ", поиск занял %.2f ms%n", duration);


        start = System.nanoTime();
        containsInput = linkedList.contains(input);
        duration = (double) (System.nanoTime() - start) / 1e+6;
        System.out.printf("Поиск перебором LinkedList: " + (containsInput ? "номер найден" : "номер не найден") +
                ", поиск занял %.2f ms%n", duration);


        Collections.sort(list);


        start = System.nanoTime();
        containsInput = (Collections.binarySearch(list, input) >= 0);
        duration = (double) (System.nanoTime() - start) / 1e+6;
        System.out.printf("Бинарный поиск в List: " + (containsInput ? "номер найден" : "номер не найден") +
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


        System.out.println(input);
    }
}

