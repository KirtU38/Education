package ducks.ducks;

import ducks.flyingBehavior.FlyNoWay;
import ducks.flyingBehavior.FlyWithWings;
import ducks.flyingBehavior.FlyingBehavior;
import ducks.quackBehavior.QuackBehavior;
import ducks.quackBehavior.QuackMuted;
import ducks.quackBehavior.QuackNormally;

public class NormalDuck extends Duck {

  // Поля у нас protected, так что можно не создавать даже, мы их Наслудем

  // Можно сделать через Конструктор
  public NormalDuck() {
    this.flyingBehavior = new FlyWithWings();
    this.quackBehavior = new QuackNormally();
  }
}
// Для Наследников от Duck нужно только Определить какое именно FlyingBehavior и QuackBehavior
// создать, от этого и будет зависеть поведение именно этой Имплементации Утки.

// Для тестов добавим сеттеры
