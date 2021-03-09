package ducks.quackBehavior;

public class QuackLikeHuman implements QuackBehavior {

  @Override
  public void quack() {
    System.out.println("Hello I'm a duck");
  }
}
