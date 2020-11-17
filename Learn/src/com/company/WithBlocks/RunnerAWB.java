package com.company.WithBlocks;

public class RunnerAWB implements Runnable {

    TestWithBlocks testWB;

    public RunnerAWB(TestWithBlocks testWB) {
        this.testWB = testWB;
    }

    @Override
    public void run() {
        System.out.println("STARTED   " + Thread.currentThread().getName());
        for (int i = 0; i < 10; i++) {
            testWB.incrementA();
        }
    }
}
