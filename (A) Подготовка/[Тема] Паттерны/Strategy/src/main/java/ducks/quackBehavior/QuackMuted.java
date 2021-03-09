package ducks.quackBehavior;

public class QuackMuted implements QuackBehavior {

  @Override
  public void quack() {
    System.out.println("...");
  }
}
