package com.company;
import java.util.concurrent.*;

public class CallableMerge implements Callable<int[]> {

    int[] array;

    public CallableMerge(int[] array) {
        this.array = array;
    }

    @Override
    public int[] call() throws Exception {

        if (array.length <= 1) {
            return array;
        }

        int mid = array.length / 2;
        int[] leftArray = new int[mid];
        System.arraycopy(array, 0, leftArray, 0, mid);
        int[] rightArray = new int[array.length - mid];
        System.arraycopy(array, mid, rightArray, 0, array.length - mid);

        CallableMerge leftTask = new CallableMerge(leftArray);
        CallableMerge rightTask = new CallableMerge(rightArray);


        if (array.length < (Main.number / 2)) {
            leftArray = leftTask.call();
            rightArray = rightTask.call();
        } else {
            FutureTask<int[]> leftFuture = new FutureTask<>(leftTask);
            FutureTask<int[]> rightFuture = new FutureTask<>(rightTask);

            Thread threadLeft = new Thread(leftFuture, "LEFT");
            Thread threadRight = new Thread(rightFuture, "RIGHT");

            threadLeft.start();
            threadRight.start();

            leftArray = leftFuture.get();
            rightArray = rightFuture.get();
        }

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
}
