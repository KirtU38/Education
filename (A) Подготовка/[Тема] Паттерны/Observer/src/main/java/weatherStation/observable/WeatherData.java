package weatherStation.observable;

import weatherStation.observers.WeatherObserver;

import java.util.ArrayList;
import java.util.List;

// Этот Обьект является Observable, то есть Субьектом, и именно от него будут зависеть другие
// Обьекты, и он будет их оповещать об изменениях датчиков температуры и тд.
// У него есть данные о погоде, температуре и тд, и он рассылает эти данные на разные дисплеи в
// устройстве.
public class WeatherData implements WeatherSubject {

  private List<WeatherObserver> weatherObservers;
  private double temperature;
  private double humidity;
  private double pressure;

  public WeatherData() {
    this.weatherObservers = new ArrayList<>();
  }

  public void registerObserver(WeatherObserver observer) {
    weatherObservers.add(observer);
  }

  public void removeObserver(WeatherObserver observer) {
    weatherObservers.remove(observer);
  }

  public void notifyObservers() {
    weatherObservers.forEach(o -> o.update(temperature, humidity, pressure));
  }

  // Вот главный метод, когда мы вносим новые значения, он в конце вызывает метод notifyObservers()
  public void setMeassurments(double temperature, double humidity, double pressure) {
    this.temperature = temperature;
    this.humidity = humidity;
    this.pressure = pressure;
    notifyObservers();
  }
}
