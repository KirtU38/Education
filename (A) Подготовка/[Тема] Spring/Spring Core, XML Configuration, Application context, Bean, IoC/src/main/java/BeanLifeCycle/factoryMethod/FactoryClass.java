package BeanLifeCycle.factoryMethod;

public class FactoryClass {

    private FactoryClass(){}

    public static FactoryClass getFactoryObject(){
        return new FactoryClass();
    }

    public void simpleMethod(){
        System.out.println("FactoryClass simple method");
    }
}
// Иногда мы не хотим чтобы Обьект Класса можно было создать через Конструктор, а только через FactoryMethod
// Тогда в Конфигурации Спринга нужно дать ему знать, что Бин будет создаваться через FactoryMethod

// Фабричные методы обязаны быть Static
