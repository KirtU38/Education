package com.company;

import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

public class Main {

    static int number = 10000000;

    public static void main(String[] args) throws Exception {

        //int[] array = {8, 1, 4, 9, 2, 5, 0, 6, 7};
        Random random = new Random();

        int[] array = IntStream.range(1, number).toArray();

        for (int i = 0; i < array.length; i++) {
            int randomIndex = random.nextInt(number - 1);
            int changedNumber = array[randomIndex];
            array[randomIndex] = array[i];
            array[i] = changedNumber;
        }

        /*// MergeSort value
        long start = System.currentTimeMillis();
        int[] mergeSort = splitValue(array);                             // 38 ms 100.000 // 308 ms 1.000.000
        System.out.println(System.currentTimeMillis() - start + " ms");  // 1426 ms 10 mil // 10774 ms 50 mil(thres 1.000.000)
*/

        // MergeSort ForkJoin
        ForkJoinPool pool = new ForkJoinPool(8);
        //ForkJoinPool pool = ForkJoinPool.commonPool();
        long start6 = System.currentTimeMillis();
        int[] fj = pool.invoke(new ForkJoinMerge(array));
        System.out.println(System.currentTimeMillis() - start6 + " ms"); // 1.155 ms 1.000.000 // 1587 ms 10 mil
        System.out.println(fj.length);                                   // 6272 ms 1.000.000 thres на 50 mil


        /*// MergeSort 2 threads
        long start7 = System.currentTimeMillis();
        CallableMerge callable = new CallableMerge(array);
        int[] split = callable.call();                                   // 7914 ms 50 mil
        System.out.println(System.currentTimeMillis() - start7 + " ms"); // 215 ms 1.000.000 круто))
        //System.out.println(Arrays.toString(split));*/


        /*// MergeSort void
        long start1 = System.currentTimeMillis();
        splitVoid(array);
        System.out.println(System.currentTimeMillis() - start1 + " ms"); // 38 ms 100.000*/

        /*// BubbleSort value
        long start2 = System.currentTimeMillis();
        int[] bubbleValue = bubbleValue(array);
        System.out.println(System.currentTimeMillis() - start2 + " ms"); // 24.465 ms 100.000
        //System.out.println(Arrays.toString(bubbleValue));*/

        /*// BubbleSort value
        long start3 = System.currentTimeMillis();
        bubbleValue(array);
        System.out.println(System.currentTimeMillis() - start3 + " ms"); // 24.283 ms 100.000
        //System.out.println(Arrays.toString(array));*/

        /*// InsertSort void
        long start4 = System.currentTimeMillis();
        insertSortVoid(array);
        System.out.println(System.currentTimeMillis() - start4 + " ms"); // 4356 ms 100.000*/

        /*// InsertSort value
        System.out.println(Arrays.toString(array));
        long start5 = System.currentTimeMillis();
        int[] insertSortValue = insertSortValue(array);
        System.out.println(System.currentTimeMillis() - start5 + " ms"); // 4356 ms 100.000
        System.out.println(Arrays.toString(insertSortValue));*/

    }

    public static int[] bubbleValue(int[] array) {

        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int changedNumber = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = changedNumber;
                }
            }
        }

        return array;
    }

    public static void bubbleVoid(int[] array) {

        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int changedNumber = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = changedNumber;
                }
            }
        }
    }

    public static int[] insertSortValue(int[] array) {

        int[] finalArray = new int[array.length];

        for (int i = 0; i < array.length; i++) {
            int minNumber = array[i];
            int indexOfMin = i;
            for (int j = i; j < array.length; j++) {
                if(array[j] < minNumber){
                    minNumber = array[j];
                    indexOfMin = j;
                }
            }
            int swapped = array[i];
            array[i] = minNumber;
            array[indexOfMin] = swapped;
            finalArray[i] = minNumber;
        }
        return finalArray;
    }

    public static void insertSortVoid(int[] array) {

        for (int i = 0; i < array.length; i++) {
            int minNumber = array[i];
            int indexOfMin = i;
            for (int j = i; j < array.length; j++) {
                if(array[j] < minNumber){
                    minNumber = array[j];
                    indexOfMin = j;
                }
            }
            int swapped = array[i];
            array[i] = minNumber;
            array[indexOfMin] = swapped;
        }
    }

    // MergeSort
    public static int[] splitValue(int[] array) {

        if (array.length <= 1) {
            return array;
        }

        int mid = array.length / 2;
        int[] leftArray = new int[mid];
        System.arraycopy(array, 0, leftArray, 0, mid);
        int[] rightArray = new int[array.length - mid];
        System.arraycopy(array, mid, rightArray, 0, array.length - mid);

        leftArray = splitValue(leftArray);
        rightArray = splitValue(rightArray);

        return mergeValue(leftArray, rightArray);
    }

    private static int[] mergeValue(int[] leftArray, int[] rightArray) {

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

        return finalArray;
    }

    // MergeSort
    public static void splitVoid(int[] array) {

        if (array.length <= 1) {
            return;
        }

        int mid = array.length / 2;
        int[] leftArray = new int[mid];
        System.arraycopy(array, 0, leftArray, 0, mid);
        int[] rightArray = new int[array.length - mid];
        System.arraycopy(array, mid, rightArray, 0, array.length - mid);

        splitVoid(leftArray);
        splitVoid(rightArray);

        mergeVoid(leftArray, rightArray, array);
    }

    private static void mergeVoid(int[] leftArray, int[] rightArray, int[] array) {

        int leftArrow = 0;
        int rightArrow = 0;

        for (int i = 0; leftArrow < leftArray.length && rightArrow < rightArray.length; i++) {
            if (leftArray[leftArrow] < rightArray[rightArrow]) {
                array[i] = leftArray[leftArrow];
                leftArrow++;
            } else {
                array[i] = rightArray[rightArrow];
                rightArrow++;
            }
        }

        int commonIndex = leftArrow + rightArrow;

        if (leftArrow == leftArray.length) {
            for (int i = commonIndex; i < array.length; i++) {
                array[i] = rightArray[rightArrow];
                rightArrow++;
            }
        } else {
            for (int i = commonIndex; i < array.length; i++) {
                array[i] = leftArray[leftArrow];
                leftArrow++;
            }
        }
    }
}


