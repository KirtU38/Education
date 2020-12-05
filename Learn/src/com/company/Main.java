package com.company;

import java.util.*;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {

        //int[] array = {8, 1, 4, 9, 2, 5, 0, 6, 7};
        Random random = new Random();

        int[] array = IntStream.range(1, 1000001).toArray();

        for (int i = 0; i < array.length; i++) {
            int randomIndex = random.nextInt(1000000);
            int changedNumber = array[randomIndex];
            array[randomIndex] = array[i];
            array[i] = changedNumber;
        }

        long start = System.currentTimeMillis();
        int[] newArr = split(array);
        System.out.println(System.currentTimeMillis() - start + " ms"); // 230 ms

        long start = System.currentTimeMillis();
        int[] newArr1 = bubble(array);
        System.out.println(System.currentTimeMillis() - start + " ms"); // 230 ms
    }

    private static int[] bubble(int[] array) {

        for (int i = 0; i < array.length; i++) {
            if (array[i] > array[i + 1]) {
                int changedNumber = array[i];
                array[i] = array[i + 1];
                array[i + 1] = changedNumber;
            }
        }


        return array;
    }

    public static int[] split(int[] array) {
        //System.out.println(Arrays.toString(array));

        if (array.length <= 1) {
            return array;
        }

        int mid = array.length / 2;
        int[] leftArray = new int[mid];
        System.arraycopy(array, 0, leftArray, 0, mid);
        int[] rightArray = new int[array.length - mid];
        System.arraycopy(array, mid, rightArray, 0, array.length - mid);

        leftArray = split(leftArray);
        rightArray = split(rightArray);

        return merge(leftArray, rightArray);
    }

    private static int[] merge(int[] leftArray, int[] rightArray) {
        //System.out.println(Arrays.toString(leftArray) + Arrays.toString(rightArray) + " ВОШЛИ В MERGE");

        int[] finalArray = new int[leftArray.length + rightArray.length];
        int leftArrow = 0;
        int rightArrow = 0;

        for (int i = 0; leftArrow < leftArray.length && rightArrow < rightArray.length; i++) {
            if (leftArray[leftArrow] < rightArray[rightArrow]) {
                finalArray[i] = leftArray[leftArrow];
                leftArrow++;
            } else {
                finalArray[i] = rightArray[rightArrow];
                rightArrow++;
            }
        }

        int commonIndex = leftArrow + rightArrow;
        if (leftArrow == leftArray.length) {
            for (int i = commonIndex; i < finalArray.length; i++) {
                finalArray[i] = rightArray[rightArrow];
                rightArrow++;
            }
        } else {
            for (int i = commonIndex; i < finalArray.length; i++) {
                finalArray[i] = leftArray[leftArrow];
                leftArrow++;
            }
        }


        //System.out.println(Arrays.toString(finalArray) + " ВЫШЛИ ИЗ MERGE");
        return finalArray;
    }
}


