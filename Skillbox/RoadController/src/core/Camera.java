package core;

public class Camera {

    public static Car getNextCar() {

        String randomNumber = Double.toString(Math.random()).substring(2, 5); // Переменная типа String
        int randomHeight = (int) (1000 + 3500. * Math.random()); // Переменная типа int
        double randomWeight = 600 + 10000 * Math.random(); // Переменная типа double

        // Здесь все характеристики машина получает через методы set
        Car car = new Car();
        car.setNumber(randomNumber);
        car.setHeight(randomHeight);
        car.setWeight(randomWeight);
        car.setHasVehicle(Math.random() > 0.5);
        car.setSpecial(Math.random() < 0.15);

        return car;
    }
}