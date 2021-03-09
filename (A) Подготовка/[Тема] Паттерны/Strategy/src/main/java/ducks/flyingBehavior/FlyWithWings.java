package ducks.flyingBehavior;

public class FlyWithWings implements FlyingBehavior {

  @Override
  public void fly() {
    System.out.println("Flying like a duck");
  }
}
