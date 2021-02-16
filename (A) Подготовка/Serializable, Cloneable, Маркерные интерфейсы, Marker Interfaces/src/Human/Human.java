package Human;

import java.io.Serializable;

public class Human implements Serializable, Cloneable {

    // Уникальный идентификатор
    private static final long serialVersionUID = 1L;

    // А это обьект в наших instance variables, который не Serializable
    private Wife wife;
    private String name;

    private int age;

    public Human(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void setWife(Wife wife) {
        this.wife = wife;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Wife getWife() {
        return wife;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    // Это наша личная реализация, для которой не нужно имплементить Cloneable интерфейс
//    @Override
//    protected Human.Human clone() throws CloneNotSupportedException {
//
//        return new Human.Human(this.getName(), this.getAge());
//    }


}
