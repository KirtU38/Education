public class Truck extends Car {

    private static int truckCount = 5;

    static {
        System.out.println("static child-class блок и переменные " + truckCount);
    }

    private int yearOfManufacture;
    private String model;
    private int maxSpeed = 250;

    {
        System.out.println("обычный child-class блок и переменные " + maxSpeed);
    }

    public Truck(int yearOfManufacture, String model, int maxSpeed) {
        System.out.println("child-class конструктор Truck\n");
        this.yearOfManufacture = yearOfManufacture;
        this.model = model;
        this.maxSpeed = maxSpeed;

        Car.carCounter++;
        truckCount++;
    }
}