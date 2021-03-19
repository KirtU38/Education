package com.test;

public class Car {
  private String carName;
  private Wheels wheels;
  private Engine engine;

  public Car(String carName) {
    this.carName = carName;
  }

  public Car(
      String carName, int numberOfWheels, String wheelsBrand, int enginePower, String engineName) {

    wheels = this.new Wheels(numberOfWheels, wheelsBrand);
    engine = this.new Engine(enginePower, engineName);
    this.carName = carName;
  }

  public void printEngineInfo() {
    engine.printEngine();
  }

  public void printWheelsInfo() {
    wheels.printWheels();
  }

  public void setWheels(Wheels wheels) {
    this.wheels = wheels;
  }

  public void setEngine(Engine engine) {
    this.engine = engine;
  }

  private class Wheels {
    private int numberOfWheels;
    private String brand;

    public Wheels(int numberOfWheels, String brand) {
      this.numberOfWheels = numberOfWheels;
      this.brand = brand;
    }

    public void printWheels() {
      System.out.println(numberOfWheels + " " + brand + " " + carName);
      engine.printEngine();
    }
  }

  private class Engine {
    private int power;
    private String brand;

    public Engine(int power, String brand) {
      this.power = power;
      this.brand = brand;
    }

    public void printEngine() {
      System.out.println(power + " " + brand + " " + carName);
    }
  }
}
