package com.test.classes;

import com.test.classes.animal.Animal;
import com.test.classes.human.Human;
import com.test.classes.human.Man;

import java.util.ArrayList;
import java.util.List;

public class Test<T> {

  public void printCollection(List<? extends Human> list) {
    Human human = list.get(0);
  }

  public static void main(String[] args) {
    Test test = new Test();
    List<Human> list = new ArrayList<>();
    test.printCollection(list);
  }
}
