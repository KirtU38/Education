import Human.Human;

import java.util.ArrayList;
import java.util.List;

public class ClonaebleTest {

    public static void main(String[] args) throws CloneNotSupportedException {

        Human human = new Human("Egor", 25);
        Human humanClone = (Human) human.clone();
        System.out.println(humanClone.getAge() + " " + humanClone.getName());

        // Тест с Листами, проверка является ли клон ссылкой на один и тот же обьект
        ArrayList<Integer> listOfInts = new ArrayList<>(List.of(1, 2, 3, 4, 5));
        ArrayList listClone = (ArrayList) listOfInts.clone();
        System.out.println(listOfInts + " original");
        System.out.println(listClone + " clone");

        listOfInts.clear();
        System.out.println(listOfInts + " original после clear()");
        System.out.println(listClone + " clone");
        System.out.println("Наш клон всё такие отдельный обьект со своим значением, и не ссылается на один  тот же лист");
    }
}
// Если мы хотим использовать дефолтную реализацию метода clone(), тогда нам нужно пометить класс Human.Human
// маркерным интерфейсом Cloneable и Override метод clone().

// Однако если у нас своя реализация метода clone(), тогда класс можно не помечать Cloneable
