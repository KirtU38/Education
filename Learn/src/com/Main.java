package com;


import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Main {

    static long numOfAllOperations = 2000000000L; // 2 миллиарда // 1641 мсек обычное сложение // 871 с ForkJoin
    static int numOfCores = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) {

        // Без ForkJoin
        /*long num = 0;

        long start = System.currentTimeMillis();
        for (int i = 0; i < numOfOperations; i++) {
            num++;
        }
        System.out.println(System.currentTimeMillis()-start);
        System.out.println(num);*/

        long start = System.currentTimeMillis();
        ForkJoinPool pool = new ForkJoinPool(numOfCores);
        System.out.println(pool.invoke(new MyFork(0, numOfAllOperations)));
        System.out.println(System.currentTimeMillis()-start);

    }

    static class MyFork extends RecursiveTask<Long> {

        long from;
        long to;

        public MyFork(long from, long to) {
            this.from = from;
            this.to = to;
        }

        @Override
        protected Long compute() {
            long numOfOperations = to - from;
            long numOfOperationsForEachThread = numOfAllOperations / numOfCores; // Кол-во операций для одного потока

            if (numOfOperations <= numOfOperationsForEachThread) { // Если операции уже поделились как надо, кол-во операция для одного потока уже правильное, то исполняем код
                long x = 0;
                for (long i = from; i <= to; i++) {
                    x++;
                }
                return x;
            } else {                                         // Если нет, то делим операцию ещё раз
                long middle = (to + from) / 2;
                MyFork firstHalf = new MyFork(from, middle);
                firstHalf.fork();
                MyFork secondHalf = new MyFork(middle + 1, to);
                secondHalf.fork();
                return secondHalf.join() + firstHalf.join();
            }
        }
    }
}




