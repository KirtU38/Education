package weatherStation.observable;

import weatherStation.observers.WeatherObserver;

// Интерфейс Observable или "Наблюдаемого", он оповещает всех об изменениях внутри себя
public interface WeatherSubject {
    void registerObserver(WeatherObserver observer);
    void removeObserver(WeatherObserver observer);
    void notifyObservers();
}
