package ru.beloshitsky;

public class Arithmetic {
  private int x;
  private int y;

  public Arithmetic(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int sum() {
    return x + y;
  }

  public int difference() {
    return x - y;
  }

  public int product() {
    return x * y;
  }

  public int avg() {
    return (x + y) / 2;
  }

  public int max() {
    return x >= y ? x : y;
  }

  public int min() {
    return x <= y ? x : y;
  }
}
