package weatherStation.observers;

// Интерфейс Observera или "Наблюдателя"
// У нас он обьеденяет рахные дисплеи на устройстве отслеживания погоды, главный дисплей, дисплей
// статистики и прогноза погоды
public interface WeatherObserver {

  void update(double temperature, double humidity, double pressure);
}
