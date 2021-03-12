package weatherStation.observers;

import weatherStation.display.Display;

public class StatisticDisplay implements WeatherObserver, Display {

  private double temperature;
  private double humidity;
  private double pressure;

  @Override
  public void update(double temperature, double humidity, double pressure) {
    this.temperature = temperature;
    this.humidity = humidity;
    this.pressure = pressure;
  }

  @Override
  public void display() {
    // Допустим ещё считает какую-нибудь статистику
    System.out.printf("Statistics%nTemperature: %s%nHumidity: %s%nPressure: %s%n%n", temperature, humidity, pressure);
  }
}
