package com.anonymousClasses;

public class Human {
  private int salary;

  public Human(int salary) {
    this.salary = salary;
  }

  public void speak() {
    System.out.println("My salary is " + salary);
    saySecretCode();
  }

  private void saySecretCode() {
    System.out.println("My code is 1901923");
  }

  public int getSalary() {
    return salary;
  }
}
