package com;

import com.Classes.Humanoid;

import java.util.List;

public class SimpleGenericClass<T> {
  T object;

  public SimpleGenericClass(T object) {
    this.object = object;
  }

  public T getObject() {
    return object;
  }

  public void setObject(T object) {
    this.object = object;
  }

  public Class<?> getType() {
    return object.getClass();
  }
  
  public void qweqwe(List<Humanoid> list){
  
  }

  public static void main(String[] args) {
    SimpleGenericClass<String> test = new SimpleGenericClass<>("Egor");
  }
}
// Мы не можем задать Типом Элементов Класса <T super Human>, тк теряется смысл Обобщения.
// Например List<String> дает гарантию что у всех Обьектов из этого Листа будут Методы Стринга, тк
// там будут либо Стринги либо его Наследники.
// Если задать <? super String>, то у нас вообще никаких гарантий, Обьекты из Листа могут иметь
// Методы Стринга, а могут не иметь и быть вообще типа Object.
