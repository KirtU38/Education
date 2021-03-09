package ducks.flyingBehavior;

public class FlyWithRocket implements FlyingBehavior {

  @Override
  public void fly() {
    System.out.println("Flying on a rocket baby!!");
  }
}
