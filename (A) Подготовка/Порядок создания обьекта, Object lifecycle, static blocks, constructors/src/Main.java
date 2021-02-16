import java.io.*;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException, ExecutionException, InterruptedException {

        Truck truck = new Truck(1989, "S-model", 180);
        Truck truck1 = new Truck(1989, "S-model", 180);
        Truck truck2 = new Truck(1989, "S-model", 180);
    }
}
// Порядок создания обьекта

// Выделяется память в JVM
// JVM создает ссылку на обьект
// Создаются переменные класса и конструктор

// static блоки и переменные Родителя
// static блоки и переменные Наследника
// Обычные блоки и переменные Родителя
// Конструктор Родителя
// Обычные блоки и переменные Наследника
// Конструктор Наследника

// Укороченная общая логика:
// static блоки и переменные(+у наследника) -> обычные переменные -> Конструктор






// Тэги: как создается обьект, последовательность создания обьекта, создание обьекта, object lifecycle