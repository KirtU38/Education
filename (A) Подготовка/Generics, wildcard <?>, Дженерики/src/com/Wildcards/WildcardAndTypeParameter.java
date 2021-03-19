package com.Wildcards;

import com.Classes.Humanoid;
import com.Classes.HumanoidChild;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WildcardAndTypeParameter {

    // Принимает ТОЛЬКО массивы, которые наследуют от Collection с type parameter от Object
    public static void printCollectionOfObjects(Collection<Object> collection) {

        System.out.println("Collection<Object>");
        for (Object element : collection) {
            System.out.println(element);
        }
        System.out.println();
    }

    // Этот "?" и есть wildcard, он имеет соответствие с любым типом, то есть здесь мы может передать Коллекцию
    // с ЛЮБЫМ type parameter или без него
    public static void printCollectionOfAnyType(Collection<?> collection) {

        System.out.println("Collection<?>");
        for (Object element : collection) {
            System.out.println(element);
        }
        System.out.println();
    }

    // Принимает любую Коллекцию, type parameter`ом которой будет обьект Human либо его Наследники (HumanChild)
    // Либо Коллекцию без type parameter вообще, но будет ошибка внутри метода, тк мы итерируемся по Human
    // Здесь добавим return type класса, который будет передан в метод, а не воид
    // Return type метода читается "Любой наследник Collection с type parameter который наследует от Human"
    // В параметр зайдет "Любая Коллекция наследник Collection в type parameter который наследует от Human"
    // Это "ограничение сверху", то есть отрезаем Классы сверху по Иерархии
    // Метод просто проходит по Коллекции и возвращает её обратно
    public static <C extends Collection<? extends Humanoid>> C getCollectionOfHumanOrChild(C collection) {

        System.out.println("Collection<? extends Human>");
        for (Humanoid humanoid : collection) {
            System.out.println(humanoid);
        }
        System.out.println();
        return collection;
    }

    // Принимает любую Коллекцию, type parameter`ом которой будет обьект Human либо его Наследники (LivingOrganism)
    // Return type метода читается "Любой наследник Collection с type parameter который является Родителем Human"
    // В параметр зайдет "Любая Коллекция наследник Collection в type parameter который является Родителем Human"
    // Это "ограничение снизу", то есть отрезаем Классы снизу по Иерархии
    // Метод просто проходит по Коллекции и возвращает её обратно
    public static <C extends Collection<? super Humanoid>> C getCollectionOfHumanOrParent(C collection) {

        System.out.println("Collection<? extends Human>");
        for (Object o : collection) {

        }
        System.out.println();
        return collection;
    }

    public static void main(String[] args) {

        // Закомментированные значит выдают ошибку

        // Здесь по сути может быть любой Обьект, однако если у Коллекции будет ЯВНО указан type parameter тип любой кроме
        // Object, то будет ошибка
        printCollectionOfObjects(new ArrayList<>(List.of("Egor", "Tasia")));
        printCollectionOfObjects(new ArrayList<Object>(List.of(2, 4, 2)));
        // WildcardGenericClass.printCollection(new ArrayList<String>());

        // Метод с wildcard, куда можно передать Коллекцию с любым type parameter или вообще без него (ну это по сути <Object>)
        printCollectionOfAnyType(new ArrayList<String>(List.of("Egor", "Tasia")));
        printCollectionOfAnyType(new ArrayList<Integer>(List.of(1, 5, 78)));
        printCollectionOfAnyType(new ArrayList(List.of(1, "String", 2.567)));

        // Метод принимает Collection и её наследников с Дженериком Human или его Наследников
        getCollectionOfHumanOrChild(new ArrayList<>());
        getCollectionOfHumanOrChild(new ArrayList<Humanoid>(List.of(new Humanoid())));
        getCollectionOfHumanOrChild(new ArrayList<HumanoidChild>(List.of(new HumanoidChild())));
        // WildcardGenericClass.printCollectionOfHumanOrChild(new ArrayList<LivingOrganism>(List.of(new LivingOrganism())));

        // Метод принимает Collection и её наследников с Дженериком Human или его Родителей
        getCollectionOfHumanOrParent(new ArrayList<Object>(List.of(new Object())));
        getCollectionOfHumanOrParent(new ArrayList<Humanoid>(List.of(new Humanoid())));
        // WildcardGenericClass.printCollectionOfHumanOrParent(new ArrayList<HumanChild>(List.of(new HumanChild())));

        // Тест List<?>
        // Его можно запихнуть только в 2 варианта,
        // это без типа элементов (Collection col) и (Collection<?> col) с неизвестным(любым) типом элементов
        // Такой Лист list of unknown СЛИШКОМ обобщенный, компилятор не будет понимать какой тип данных будет в листе,
        // а это и есть цель Дженериков, проверка типов.
        // Но в то же время это значит что переменная может быть Ссылкой для любого другого Листа;
        List<?> listOfUnknown = new ArrayList<>();
        List<String> listOfStrings = new ArrayList<>(List.of("Egor", "Tasia"));
        listOfUnknown = listOfStrings;
        // listOfUnknown.add("Egor");
        getUnknown(listOfUnknown);
        getUnknownInParameters(listOfUnknown);
    }

    // Указали нужные параметры в return type
    public static  void getUnknown(Collection collection) {

        System.out.println("Collection collection");
    }

    public static void getUnknownInParameters(Collection<?> collection) {

        System.out.println("Collection<?> collection");
    }
}
//   Иерархия:
//
//     Object
//       |
//  LivingOrganism
//       |
//     Human
//       |
//   HumanChild

// Wildcard - это указатель в type parameter(параметр типа), который означает "Обьект любого типа"

// Upper Bounded Wildcard или "Огранчиение сверху" - это wildcard с ключевым словом extends, то есть он позволяет
// использовать только указанный Класс и его Наследников

// Lower Bounded Wildcar "Ограничение снизу" - это wildcard с ключевым словом super, то есть он позволяет использовать
// только указанный Класс и его Родителей

// <Тип внутри> - называется type parameter, element type или "параметр типа".





// Тэги: внутри <>, <внутри>