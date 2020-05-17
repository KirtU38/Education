
public class Loader {
    public static void main(String[] args) {
        Cat cat1 = new Cat();
        Cat cat2 = new Cat();
        Cat cat3 = new Cat();
        Cat cat4 = new Cat();
        Cat cat5 = new Cat();

        // Вес кошек
        System.out.println("Вес кошек:");
        System.out.println(cat1.getWeight());
        System.out.println(cat2.getWeight());
        System.out.println(cat3.getWeight());
        System.out.println(cat4.getWeight());
        System.out.println(cat5.getWeight());
        System.out.println("===============================");

        // Покормить 2 кошки и распечатать вес
        System.out.println("Покормить 2 кошки и распечатать вес:");
        System.out.println(cat3.getWeight() + " - До"); // До
        cat3.feed(150.0);
        System.out.println(cat3.getWeight() + " - После(150г)"); // После

        System.out.println(cat5.getWeight() + " - До"); // До
        cat5.feed(100.0);
        System.out.println(cat5.getWeight() + " - После(100г)"); // После
        System.out.println("===============================");

        // Перекормить кошку
        System.out.println("Перекормить кошку:");
        while (cat2.getWeight() <= 9000) {
            cat2.feed(200.0);
            System.out.println(cat2.getWeight());
            System.out.println(cat2.getStatus());
        }
        System.out.println("===============================");

        // Замяукать до смерти
        System.out.println("Замяукать до смерти:");
        while (cat1.getWeight() >= 1000) {
            cat1.meow();
            System.out.println(cat1.getWeight());
            System.out.println(cat1.getStatus());
        }
        System.out.println("===============================");

        // Метод, который возвращает сумму сьеденой еды
        System.out.println("Метод, который возвращает сумму съеденной еды:");
        System.out.println(cat4.getWeight() + " - Начальный вес");
        cat4.feed(150.0);
        System.out.println(cat4.getWeight() + " - Покормили 150г");
        cat4.drink(100.0);
        System.out.println(cat4.getWeight() + " - Напоили 100г");
        cat4.pee();
        System.out.println(cat4.getWeight() + " - Сходили в туалет 10г");
        System.out.println(cat4.getAmountEaten() + " - Узнали сколько именно покормили");
        System.out.println("===============================");

        // Кошка идет в туалет
        System.out.println("Метод, который говорит кошке сходить в туалет: ");
        System.out.println(cat3.getWeight() + " - До");
        cat3.pee();
        System.out.println(cat3.getWeight() + " - Уменьшает вес на 10г");
        System.out.println("===============================");

        // Перепроверяем вес и кто остался жив, чтобы проверить следующий метод
        System.out.println("Проверяем кто должен остаться жив:");
        System.out.println(cat1.getWeight() + " - " + cat1.getStatus());
        System.out.println(cat2.getWeight() + " - " + cat2.getStatus());
        System.out.println(cat3.getWeight() + " - " + cat3.getStatus());
        System.out.println(cat4.getWeight() + " - " + cat4.getStatus());
        System.out.println(cat5.getWeight() + " - " + cat5.getStatus());
        System.out.println("===============================");

        // Проверяем метод getCount:
        System.out.println("Проверяем метод getCount:");
        System.out.println("Сейчас их - " + Cat.getCount());
        Cat cat6 = new Cat();
        Cat cat7 = new Cat();
        System.out.println("Добавили 2, теперь их - " + Cat.getCount());
        cat6.feed(8000.0); // Перекармливаем
        System.out.println("Перекормили 1 кошку, теперь их - " + Cat.getCount());
        System.out.println("===============================");

        // Проверяем точность ещё раз
        System.out.println("Проверяем точность метода:");
        System.out.println(cat1.getWeight() + " - " + cat1.getStatus());
        System.out.println(cat2.getWeight() + " - " + cat2.getStatus());
        System.out.println(cat3.getWeight() + " - " + cat3.getStatus());
        System.out.println(cat4.getWeight() + " - " + cat4.getStatus());
        System.out.println(cat5.getWeight() + " - " + cat5.getStatus());
        System.out.println(cat6.getWeight() + " - " + cat6.getStatus());
        System.out.println(cat7.getWeight() + " - " + cat7.getStatus());
        System.out.println("4 живы, значит всё верно");
        System.out.println("===============================");

        // Проверяем чтобы мертвые кошки не могли ничего делать
        System.out.println("Мертвые кошки не должны ничего делать:");
        System.out.println("Пытаемся кормить...");
        cat1.feed(100.0);
        System.out.println("Пытаемся напоить...");
        cat1.drink(150.0);
        System.out.println("Пытаемся сходить в туалет...");
        cat1.pee();
        System.out.println("Пытаемся помяукать...");
        cat1.meow();
        System.out.println("Кошка успешно погибла:)");
        System.out.println("===============================");

        // Создаем котенка
        Cat kitten = getKitten();
        System.out.println("Котенок весом:");
        System.out.println(kitten.getWeight());
        System.out.println("===============================");

        // Создаем копию через конструктор
        System.out.println("Копия через конструктор:");
        Cat copy = new Cat(cat3);
        System.out.println(cat3.getWeight()+" - Оригинал до еды");
        System.out.println(copy.getWeight()+" - Копия до еды");
        System.out.println("Кормим только копию 200г");
        copy.feed(200.0);
        System.out.println(cat3.getWeight()+" - Оригинал после еды");
        System.out.println(copy.getWeight()+" - Копия после еды");
        System.out.println("===============================");

        // Создаем копию через метод
        System.out.println("Копия через метод:");
        Cat copy1 = Cat.copyOf(cat3);
        System.out.println(cat3.getWeight()+" - Оригинал до еды");
        System.out.println(copy1.getWeight()+" - Копия до еды");
        System.out.println("Кормим только копию 200г");
        copy1.feed(200.0);
        System.out.println(cat3.getWeight()+" - Оригинал после еды");
        System.out.println(copy1.getWeight()+" - Копия после еды");
        System.out.println("===============================");
    }

    public static Cat getKitten(){ // Метод создающий котенка с весом 1100 используя конструктор
        return new Cat(1100);
    }
}