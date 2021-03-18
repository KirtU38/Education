package com.anonymousClasses;

public class Human {
  private int salary;

  public Human(int salary) {
    this.salary = salary;
  }

  public void speak() {
    System.out.println("My age is " + salary);
  }
}
