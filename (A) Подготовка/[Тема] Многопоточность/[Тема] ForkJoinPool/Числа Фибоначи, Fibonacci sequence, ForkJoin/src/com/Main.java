package com;

import java.util.concurrent.ForkJoinPool;

public class Main {

    public static ForkJoinPool pool = ForkJoinPool.commonPool();
    static int iterations = 39;

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        // Стандартная реализация
        System.out.println(fibonacci(iterations));
        System.out.println(System.currentTimeMillis() - start + " ms standard");

        // Стандартная реализация с Массивом
        start = System.currentTimeMillis();
        System.out.println(FibWithArray.fib(iterations));
        System.out.println(System.currentTimeMillis() - start + " ms standard+Array");

        // ForkJoin реализация
        start = System.currentTimeMillis();
        FibForkJoin fibForkJoin = new FibForkJoin(iterations);
        System.out.println(pool.invoke(fibForkJoin));
        System.out.println(System.currentTimeMillis() - start + " ms ForkJoin");

        // ForkJoin реализация с Массивом
        start = System.currentTimeMillis();
        FibForkJoinWithArray fibForkJoinWithArray = new FibForkJoinWithArray(iterations);
        System.out.println(pool.invoke(fibForkJoinWithArray));
        System.out.println(System.currentTimeMillis() - start + " ms ForkJoin+Array");


    }

    // Стандартная последовательность Фибоначи, каждое число вручную опускается вниз до 1 и 0 и строится вверх по
    // цепочке, медленный способ, но классический
    public static int fibonacci(long index) {

        if (index <= 2) {
            return 1;
        } else {
            return fibonacci(index - 1) + fibonacci(index - 2);
        }
    }

}




