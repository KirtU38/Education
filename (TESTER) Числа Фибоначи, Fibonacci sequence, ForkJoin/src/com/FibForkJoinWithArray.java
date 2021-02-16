package com;

import java.util.concurrent.RecursiveTask;

public class FibForkJoinWithArray extends RecursiveTask<Integer> {

    int index;
    public static int[] listOfNumbers = new int[1000];


    public FibForkJoinWithArray(int index) {
        this.index = index;
    }


    // Это улучшенный, где как только найдено одно число, оно записывается в массив на соответствующий индекс,
    // из-за этого программе не нужно вручную опускаться вниз до 1 и 0 чтобы найти число, а она сначала проверяет
    // есть ли число в массиве, а только потом по надобности опускается вниз
    @Override
    protected Integer compute() {
        int fibNumber;

        if (listOfNumbers[index] == 0) {
            if (index <= 2) {
                fibNumber = 1;
            } else {
                FibForkJoinWithArray firstTask = new FibForkJoinWithArray(index - 1);
                firstTask.fork();
                FibForkJoinWithArray secondTask = new FibForkJoinWithArray(index - 2);
                secondTask.fork();

                int first = firstTask.join();
                int second = secondTask.join();
                fibNumber = first + second;
            }
            listOfNumbers[index] = fibNumber;
        } else {
            fibNumber = listOfNumbers[index];
        }
        return fibNumber;
    }
}
