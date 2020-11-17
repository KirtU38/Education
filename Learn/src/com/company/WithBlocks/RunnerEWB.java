package com.company.WithBlocks;

public class RunnerEWB implements Runnable {

    TestWithBlocks testWB;

    public RunnerEWB(TestWithBlocks testWB) {
        this.testWB = testWB;
    }

    @Override
    public void run() {
        System.out.println("STARTED   " + Thread.currentThread().getName());
        for (int i = 0; i < 10; i++) {
            testWB.incrementE();
        }
    }
}
