package ducks.ducks;

import ducks.flyingBehavior.FlyNoWay;
import ducks.flyingBehavior.FlyingBehavior;
import ducks.quackBehavior.QuackBehavior;
import ducks.quackBehavior.QuackMuted;

public abstract class Duck {

  protected FlyingBehavior flyingBehavior;
  protected QuackBehavior quackBehavior;

  public void swim() {
    System.out.println("All Ducks can swim");
  }

  public void performQuack() {
    quackBehavior.quack();
  }

  public void performFly(){
    flyingBehavior.fly();
  }

  public void setFlyingBehavior(FlyingBehavior flyingBehavior) {
    this.flyingBehavior = flyingBehavior;
  }

  public void setQuackBehavior(QuackBehavior quackBehavior) {
    this.quackBehavior = quackBehavior;
  }
}
// Мы создаем для "изменяемых" поведений Quack и Fly (то есть эти вещи будут меняться у всех уток)
// 2 поля Суперкласса для каждого поведения

// Создадим Сеттеры для Тестов













