package com.company.Static;

import com.company.Runner;

public class RunnerAST extends Runner implements Runnable {

    long start;

    public RunnerAST(long start) {
        this.start = start;
    }

    @Override
    public void run() {
        System.out.println("STARTED   " + Thread.currentThread().getName());
        for (int i = 0; i < numOfIncrement; i++) {
            TestStatic.incrementA();
        }
        System.out.println(System.currentTimeMillis() - start + " ms КОНЕЦ");
    }
}
