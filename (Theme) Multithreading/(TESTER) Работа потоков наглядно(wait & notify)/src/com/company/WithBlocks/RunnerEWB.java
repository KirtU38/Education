package com.company.WithBlocks;

import com.company.Runner;

public class RunnerEWB extends Runner implements Runnable {

    TestWithBlocks testWB;
    long start;

    public RunnerEWB(TestWithBlocks testWB, long start) {
        this.testWB = testWB;
        this.start = start;
    }

    @Override
    public void run() {
        System.out.println("STARTED   " + Thread.currentThread().getName());
        for (int i = 0; i < numOfIncrement; i++) {
            testWB.incrementE();
        }
        System.out.println(System.currentTimeMillis() - start + " ms КОНЕЦ");
    }
}
