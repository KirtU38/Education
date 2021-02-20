public class Main {

    static int x;

    public static void main(String[] args) {

        System.out.println(x);
        int y;
        System.out.println(y);

    }
}
// Stack, Heap, MetaSpace, Code & Garbage collector

// Локальная переменная - значит обьявлена внутри метода.

// В Stack хранятся:
// 1) Все вызванные методы
// 2) Локальные primitive переменные
// 3) Локальные ссылки на обьекты (то есть переменные, но не сами Обьекты)

// В Heap хранятся:
// 1) ВСЕ Обьекты (и все instance variables внутри них, тк они тоже часть Обьекта)

// В MetaSpace хранятся:
// 1) static переменные и методы

// В Code хранится:
// 1) bytecode

// Garbage collector - автоматически удаляет из Heap Обьекты, на которые больше никто не ссылается.
// То есть нет переменных, которым был бы присвоен этот Обьект.

// ОЧЕНЬ ВАЖНО
// Stack готовится до рантайма, и все переменные должны быть инициализированы, если мы хотим их использовать,
// у Локальных переменных нет дефолтных значений.

// Heap же формируется в рантайме, и у instance variable, то есть у переменных класса, есть дефолтные значения.

// Stack и Frame обьяснены в классе StackAndFrame



// Если что вся инфа здесь
// https://javarush.ru/groups/posts/609-prisvaivanie-i-inicializacija-v-java












