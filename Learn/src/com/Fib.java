package com;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class Fib extends RecursiveTask<Long> {

    long index;
    public static long[] listOfNumbers = new long[1000];


    public Fib(long index) {
        this.index = index;
    }


    // Это улучшенный, где как только найдено одно число, оно записывается в массив на соответствующий индекс,
    // из-за этого программе не нужно вручную опускаться вниз до 1 и 0 чтобы найти число, а она сначала проверяет
    // есть ли число в массиве, а только потом по надобности опускается вниз
    @Override
    protected Long compute() {
        long fibNumber;

        if(listOfNumbers[(int) index] == 0){
            if (index <= 1) {
                fibNumber = index;
            } else {
                Fib firstTask = new Fib(index - 1);
                firstTask.fork();
                Fib secondTask = new Fib(index - 2);
                secondTask.fork();

                Long first = firstTask.join();
                Long second = secondTask.join();
                //System.out.println(first + "   " + second + "  " + Thread.currentThread().getName());
                fibNumber = first + second;
            }
            listOfNumbers[(int) index] = fibNumber;
        } else {
            fibNumber = listOfNumbers[(int) index];
        }
        return fibNumber;
    }
}
