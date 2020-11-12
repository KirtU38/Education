package Test;

public class Main {
    public static void main(String[] args) {

        /*Parking parking = new Parking();

        new Thread(new Consumer(parking), "Consumer-1").start();
        new Thread(new Producer(parking), "Producer-1").start();
        new Thread(new Consumer(parking), "Consumer-2").start();
        new Thread(new Producer(parking), "Producer-2").start();
        new Thread(new Consumer(parking), "Consumer-3").start();
        new Thread(new Producer(parking), "Producer-3").start();
*/
        System.out.println(Thread.currentThread().getId());
    }
}
