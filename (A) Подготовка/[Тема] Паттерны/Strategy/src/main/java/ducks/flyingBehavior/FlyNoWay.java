package ducks.flyingBehavior;

public class FlyNoWay implements FlyingBehavior {

  @Override
  public void fly() {
    System.out.println("I can't fly");
  }
}
