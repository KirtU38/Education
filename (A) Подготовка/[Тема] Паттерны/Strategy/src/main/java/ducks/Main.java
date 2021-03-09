package ducks;

import ducks.ducks.Duck;
import ducks.ducks.NormalDuck;
import ducks.flyingBehavior.FlyWithRocket;

public class Main {
  public static void main(String[] args) {

    Duck normalDuck = new NormalDuck();
    normalDuck.performFly();
    normalDuck.setFlyingBehavior(new FlyWithRocket());
    normalDuck.performFly();
  }
}

