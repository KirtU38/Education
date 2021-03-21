package com.test.classes;

import com.test.classes.animal.Animal;
import com.test.classes.animal.Cat;
import com.test.classes.animal.Dog;
import com.test.classes.human.Human;
import com.test.classes.human.Man;
import com.test.classes.human.Woman;

import java.util.ArrayList;
import java.util.List;

public class Test<T> {

  // Старый
  // Принимает этот метод то же самое, но return type теперь зависит от типа элементов
  // Здесь возвратом можно поставить и Object, потому что LIvingThing наследник Object, то есть
  // LivingThing и всё что вышя ЯВЛЯЕТСЯ Object'ом
  public static LivingThing returnFirstElementOld(List<? extends LivingThing> list) {
    return list.get(0);
  }

  // Новый
  // Такая запись лучше чем запись выше, потому что дает нам гибкий return type
  public static <C extends LivingThing> C returnFirstElement(List<C> list) {
    return list.get(0);
  }
  
  // В такой метод можно передать любой Лист, тип элементов выше Cat в Иерархии, и добавлять в него
  // можно только Cat, потому что только Cat гарантированно является наследником всех Классов выше.
  // Например если мы добавим Animal, а Лист окажется <Cat>, то в List<Cat> нельзя добавить Animal,
  // тк Animal не ялвяется обязательно Cat, он может быть и Dog.
  public static void addOneElement(List<? super Cat> list) {
    list.add(new Cat());
  }
  
  public static Object getOneElementFromLowerBounded(List<? super Cat> list) {
    return list.get(0);
  }
  
  

  //              Object
  //           LivingThing
  //      Animal           Human
  //   Cat    Dog        Man    Woman

  public static void main(String[] args) {
    Object object = new Object();
    LivingThing livingThing = new LivingThing();
    Human human = new Human();
    Animal animal = new Animal();
    Cat cat = new Cat();
    Dog dog = new Dog();
    Man man = new Man();
    Woman woman = new Woman();

    // Upper-bound
    List<? extends LivingThing> upperBounded = new ArrayList<>();

    // Lower-bound
    List<? super Human> lowerBounded = new ArrayList<>();

    // Test метод List<? extends LivingThing>
    // Из такого метода выйдет LivingThing, однако мы может закастить в Cat в самом методе, либо
    // после выполнения метода в коде.
    // Внутри метода плохо, тк теряется type safety, ведь в метод можно передать List<Dog>
    List<Cat> testList = new ArrayList<>();
    testList.add(new Cat());

    // Новый метод вернет по типу переданному ему Листа, то есть Cat
    Cat catResult = returnFirstElement(testList);
    // А старый будет возвращать только LivingThing
    LivingThing livingThingResult = returnFirstElementOld(testList);
    // Однако они равны в том, что в оба метода другой можно будет передать одни и те же Листы

    // Test метод List<? super Cat>
    List<LivingThing> listTest = new ArrayList<>();
    addOneElement(listTest);
    System.out.println(listTest);
    System.out.println(getOneElementFromLowerBounded(listTest));
  }
}
