package ducks.quackBehavior;

public class QuackNormally implements QuackBehavior {

  @Override
  public void quack() {
    System.out.println("Quack");
  }
}
