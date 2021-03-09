package ducks.ducks;

import ducks.flyingBehavior.FlyNoWay;
import ducks.flyingBehavior.FlyWithRocket;
import ducks.quackBehavior.QuackMuted;

public class WoodenDuck extends Duck {

  public WoodenDuck() {
    this.flyingBehavior = new FlyWithRocket();
    this.quackBehavior = new QuackMuted();
  }
}
// Это деревянная утка, она не может Quack, ну будет допустим летать на Рокете
