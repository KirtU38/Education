public class Main {

    public static void main(String[] args) {

        // int не меняется
        //        Integer x = new Integer(10);
        //        Integer x = 10;
        int x = 10;
        System.out.println(x + " до");
        changeNumber(x);
        System.out.println(x + " после\n");

        // String не меняется
        //        String name = new String("Egor");
        String name = "Egor";
        System.out.println(name + " до");
        changeString(name);
        System.out.println(name + " после\n");

        // Object передается в void
        Human human = new Human("Egor", 25);
        System.out.println(human + " до");
        changeObject(human);
        System.out.println(human + " после\n");

        // Object передается в return метод
        Human human1 = new Human("Egor", 25);
        System.out.println(human1 + " до");
        int a = changeObjectReturn(human1);
        System.out.println(human1 + " после");
    }

    private static void changeNumber(Integer x) {

        x = 0;
        System.out.println(x + " внутри метода");
    }

    private static void changeString(String s) {

        s = s.replaceAll("\\w", "x");
        System.out.println(s + " внутри метода");
    }

    private static void changeObject(Human h) {

        h.setName("Tasia");
        h.setAge(15);
        System.out.println(h + " внутри метода");
    }

    private static int changeObjectReturn(Human h) {

        h.setName("Tasia");
        h.setAge(15);
        System.out.println(h + " внутри метода");
        return 1;
    }
}
// Java is pass-by-value = pass-by-copy
// Value - означает биты внутри переменной
// В primitive type, bit pattern внутри переменной это и есть её значение, оно копируется и метод работает с копией
// В Обьектах, переменная это только Ссылка (reference type), а значит создаётся новая переменная, которая
// ссылается на ОДИН И ТОТ ЖЕ Обьект

// При чем не важно void или return метод

// Значит Джава передает аргументом в метод только копию именно ПЕРЕМЕННОЙ
// Для примитивов это само значение(и оригинал не трогается), для Обьектов это Ссылка(и оригинал можно изменить
// через эту ссылку)

// String кстати имеет такое же поведение, как и primitive, потому что он immutable



// tags: типы переменных, переменные, pass by value














