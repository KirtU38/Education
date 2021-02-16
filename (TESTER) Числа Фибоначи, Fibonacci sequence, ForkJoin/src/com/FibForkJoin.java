package com;

import java.util.concurrent.RecursiveTask;

public class FibForkJoin extends RecursiveTask<Integer> {

    int index;
    public static int[] listOfNumbers = new int[1000];


    public FibForkJoin(int index) {
        this.index = index;
    }


    // Реализация с ForkJoin
    @Override
    protected Integer compute() {
        int fibNumber;

        if (index <= 2) {
            fibNumber = 1;
        } else {
            FibForkJoin firstTask = new FibForkJoin(index - 1);
            firstTask.fork();
            FibForkJoin secondTask = new FibForkJoin(index - 2);
            secondTask.fork();

            int first = firstTask.join();
            int second = secondTask.join();
            fibNumber = first + second;
        }
        return fibNumber;
    }
}
