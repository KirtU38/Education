package com.company;
import java.util.concurrent.RecursiveTask;


public class ForkJoinMerge extends RecursiveTask<int[]> {

    int[] array;

    public ForkJoinMerge(int[] array) {
        this.array = array;
    }

    @Override
    protected int[] compute() {

        if (array.length <= 1) {
            return array;
        }

        int mid = array.length / 2;
        int[] leftArray = new int[mid];
        System.arraycopy(array, 0, leftArray, 0, mid);
        int[] rightArray = new int[array.length - mid];
        System.arraycopy(array, mid, rightArray, 0, array.length - mid);

        ForkJoinMerge leftTask = new ForkJoinMerge(leftArray);
        ForkJoinMerge rightTask = new ForkJoinMerge(rightArray);

        if (array.length < 2_000_000) {
            leftArray = leftTask.compute();
            rightArray = rightTask.compute();
        } else {
            leftTask.fork();
            rightTask.fork();

            leftArray = leftTask.join();
            rightArray = rightTask.join();
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
