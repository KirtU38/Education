package weatherStation;

import weatherStation.observable.WeatherData;
import weatherStation.observable.WeatherSubject;
import weatherStation.observers.MainDisplay;
import weatherStation.observers.StatisticDisplay;
import weatherStation.observers.WeatherObserver;

public class Test {
  public static void main(String[] args) {
    // Создаем Субьект
    WeatherData weatherData = new WeatherData();
    // Создаем Observers, то есть наши дисплеи
    MainDisplay mainDisplay = new MainDisplay();
    StatisticDisplay statisticDisplay = new StatisticDisplay();
    // Регестрируем наших Observers у Субьекта
    weatherData.registerObserver(mainDisplay);
    weatherData.registerObserver(statisticDisplay);
    // Тест
    weatherData.setMeassurments(30, 80, 178);
    mainDisplay.display();
    statisticDisplay.display();

    weatherData.setMeassurments(-20, 37, 200);
    mainDisplay.display();
    statisticDisplay.display();
  }
}
// Всё успешно, при каждом изменении состояния Субьекта WeatherData, он автоматически оповещает всех
// Обсерверов и Обновляет их через их метод update().
