package BeanLifeCycle;

import BeanLifeCycle.factoryMethod.FactoryClass;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        TestClass testClass = context.getBean("testClass", TestClass.class);
        TestClass testClass1 = context.getBean("testClass", TestClass.class);

        testClass.simpleMethod();
        testClass1.simpleMethod();

        // Через FactoryMethod
        FactoryClass factoryClass = context.getBean("factoryClass", FactoryClass.class);
        factoryClass.simpleMethod();

        context.close();
    }
}
// 1) Конструктор Injected Класса
// 2) Конструктор Основного Класса
// 3) init-method

// Эти вещи вызываются даже если мы не вызывали context.getBean()

// Для Бинов со scope=Prototype Спринг не вызывает destroy-method, потому что Спринг отдает Prototype Бины Клиенту
// и больше о них не заботится

// Если scope=Prototype, то init-method вызывается при каждом создании нового Бина
