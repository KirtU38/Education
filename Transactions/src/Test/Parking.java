package Test;

import java.util.concurrent.atomic.AtomicInteger;

public class Parking {

    private int parkingSize = 500;
    private int carCount = 0;

    public synchronized void in() {

        if(carCount == parkingSize){
            System.out.println(Thread.currentThread().getName() + " WAIT");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        carCount++;
        System.out.println("+++ ЗАЕЗД! Место осталось " + (parkingSize - carCount) + " Машин " + carCount + "     " + Thread.currentThread().getName());
        notify();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void out() {

        if(carCount == 0){
            try {
                System.out.println(Thread.currentThread().getName() + " WAIT");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        carCount--;
        System.out.println("--- ВЫЕЗД! Место осталось " + (parkingSize - carCount) + " Машин " + carCount + "     " + Thread.currentThread().getName());
        notify();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
