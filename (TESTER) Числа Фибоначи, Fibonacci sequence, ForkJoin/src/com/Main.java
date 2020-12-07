package com;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

public class Main {

    public static ForkJoinPool pool = ForkJoinPool.commonPool();
    static long iterations = 45;


    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        System.out.println(fibonacci(iterations));
        System.out.println(System.currentTimeMillis() - start + " ms"); // 9094 ms


        long s = System.currentTimeMillis();
        Fib fib = new Fib(iterations);
        System.out.println(pool.invoke(fib));
        System.out.println(System.currentTimeMillis() - s + " ms"); // 4 ms

        System.out.println(Arrays.toString(Fib.listOfNumbers));
    }

    // Стандартная последовательность Фибоначи, каждое число вручную опускается вниз до 1 и 0 и строится вверх по
    // цепочке, медленный способ, но классический
    public static Long fibonacci(long index) {

        if (index <= 1) {
            return index;
        } else {
            return fibonacci(index - 1) + fibonacci(index - 2);
        }
    }

}




