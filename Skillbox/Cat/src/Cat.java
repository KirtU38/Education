import com.sun.tools.javac.Main;

public class Cat {
    private double originWeight;
    private double weight;

    private static final double MIN_WEIGHT = 1000.0; // Переделаны в final
    private static final double MAX_WEIGHT = 9000.0; // Переделаны в final
    private static final int EYES_COUNT = 4; // Количество глаз
    private double amountEaten; // Переменная для определения сьеденной еды
    private static int count; // Переменная для подсчета созданных кошек
    private boolean isAlive; // Булин содержащий информацию жива кошка или нет
    private CatColors color; // Переменная окраса

    public Cat() {
        weight = 1500.0 + 3000.0 * Math.random();
        originWeight = weight;
        isAlive = true;
        count++;
    }

    public Cat(double weight) { // Конструктор где мы сами задаем вес кошки
        this();
        this.weight = weight;
    }

    public Cat(Cat cat) { // Конструктор копирования
        this();
        weight = cat.weight;
    }

    public static Cat copyOf(Cat cat) { // Метод для копирования
        Cat copy = new Cat(cat.weight);
        return copy;
    }

    public void meow() {
        if (isAlive) {
            weight = weight - 1;
            System.out.println("Meow");
            checkIfAlive();
        } else {
            System.out.println("Cat is dead");
        }
    }

    public void feed(Double amount) {
        if (isAlive) {
            weight = weight + amount;
            amountEaten = amountEaten + amount;
            checkIfAlive();
        } else {
            System.out.println("Cat is dead");
        }
    }

    public void drink(Double amount) {
        if (isAlive) {
            weight = weight + amount;
            checkIfAlive();
        } else {
            System.out.println("Cat is dead");
        }
    }

    public Double getWeight() {
        return weight;
    }

    public String getStatus() {
        if (weight < MIN_WEIGHT) {
            return "Dead";
        } else if (weight > MAX_WEIGHT) {
            return "Exploded";
        } else if (weight > originWeight) {
            return "Sleeping";
        } else {
            return "Playing";
        }
    }

    public double getAmountEaten() {
        return amountEaten;
    }

    public void pee() {
        if (isAlive) {
            weight = weight - 10;
            System.out.println("Look away human");
            checkIfAlive();
        } else {
            System.out.println("Cat is dead");
        }
    }

    public static int getCount() {
        return count;
    }

    private void checkIfAlive() { // Метод для определения жива ли кошка после каждого действия
        if (isAlive) {
            if (weight > MAX_WEIGHT || weight < MIN_WEIGHT) {
                isAlive = false;
                count--;
            }
        }
    }

    public CatColors getColor() {
        return color;
    } // Получить окрас


    public void setColor(CatColors color) { // Назначить окрас
        this.color = color;
    }

    public static void main(String[] args) {
        // Домашнее задание
        System.out.println("Домашнее задание:");
        System.out.println(Cat.getCount() + " - Столько сейчас кошек");
        System.out.println("Создадим новую кошку");
        Cat cat = new Cat();
        System.out.println(Cat.getCount() + " - Столько теперь кошек");
        cat.feed(9000.0);
        System.out.println(cat.getStatus() + " - Перекармливаем и проверяем через getStatus()");
        System.out.println(Cat.getCount() + " - Столько теперь кошек");
        System.out.println("Используем метод checkIfAlive() на эту кошку");
        cat.checkIfAlive();
        System.out.println(Cat.getCount() + " - Столько теперь кошек");
        System.out.println(" - Используем метод ещё раз");
        cat.checkIfAlive();
        System.out.println(Cat.getCount() + " - Столько теперь кошек");
        System.out.println(" - Используем метод ещё раз");
        cat.checkIfAlive();
        System.out.println(Cat.getCount() + " - Столько теперь кошек");
        System.out.println("Пройдемся методом по всем кошкам");
        cat.checkIfAlive();
        System.out.println(Cat.getCount() + " - Столько теперь кошек");
        System.out.println("Количество не меняется, кажется всё работает");
    }
}
