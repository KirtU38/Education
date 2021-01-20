package com.company;

public class Main {

    public static void main(String[] args) {

        Parking parking = new Parking();
        Producer producer = new Producer(parking);
        Consumer consumer = new Consumer(parking);

        new Thread(producer).start();
        new Thread(consumer).start();
    }
}
// У нас есть класс Parking парковка, у нее есть метод вывезти машину out() и завезти in(). На парковке 500 мест,
// если количество машин больше 500, Thread который ЗАВОЗИТ(Producer) должен встать на паузу и подождать пока Thread
// который УВОЗИТ увезет машины, этого мы добьемся с помощью слов wait() и notify()
// wait() и notify() можно использовать только в synchronized методах либо внутри synchronized блоков
// Ещё это работает только внутри класса, для примера посмотри закоменченные методы в Producer и Consumer

