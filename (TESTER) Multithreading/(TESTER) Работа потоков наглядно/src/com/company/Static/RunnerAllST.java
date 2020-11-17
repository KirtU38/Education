package com.company.Static;

import com.company.Runner;

public class RunnerAllST extends Runner implements Runnable {

    long start;

    public RunnerAllST(long start) {
        this.start = start;
    }

    @Override
    public void run() {
        System.out.println("STARTED   " + Thread.currentThread().getName());
        for (int i = 0; i < numOfIncrement; i++) {
            TestStatic.incrementAll();
        }
        System.out.println(System.currentTimeMillis() - start + " ms КОНЕЦ");
    }
}
