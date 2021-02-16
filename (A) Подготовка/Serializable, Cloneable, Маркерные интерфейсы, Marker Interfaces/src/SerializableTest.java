import Human.Human;

import java.io.*;

public class SerializableTest {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        // Это поиграться с transient и не Serializable обьектами внутри класса
//        Human.Human human = new Human.Human("Egor", 25);
//        human.setWife(new Human.Wife("Tasia", 24));

        // Это обычный Human.Human без Human.Wife и тд
        Human human = new Human("Egor", 25);

        // Записываем в файл
        FileOutputStream fos = new FileOutputStream("resources/test.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fos);
        objectOutputStream.writeObject(human);
        objectOutputStream.flush();
        objectOutputStream.close();

        // Читаем из файла
        FileInputStream fileInputStream = new FileInputStream("resources/test.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Human egor = (Human) objectInputStream.readObject();
        objectInputStream.close();

        // Из файла пришел абсолютно рабочий обьект класса Human.Human
        System.out.println(egor.getAge() + " " + egor.getName());
    }
}

// Маркерный интерфейс - это интерфейс, который не имеет методов или констант. Он служит для предоставления информации
// об обьекте нашей JVM в рантайме.

// Serializable значит "Этот обьект можно превратить в поток байт".
// Это нужно для записи обьекта в .txt файл через ObjectOutputStream например

// В каждом Serializable классе должен быть UID, то есть уникальный идентификатор

// Процесс Deserializetion это обратный процесс, когда мы из набора байт(txt файл например) получаем обьекта
// Serializable класса
// Интерфейса Desirializable не существует

// При сериализации объекта сериализуются все объекты, на которые он ссылается в своих переменных
// То есть если у обьекта в его instance variables есть обьект не Serializable класса, то будет Exception
// А скорее так "Если внутри нашего Serializable обьекта будет СОЗДАННЫЙ обьект, который при этом не Serializable"
// Потому что мы можем создать Human.Human, если у него просто не задан Human.Wife, то есть ему не передан Human.Wife в обьект через setWife

// Однако если мы не имеем права делать Класс обьекта(Human.Wife как у нас) внутри класса Serializable, то мы может сделать
// этот обьект transient, это будет означать что поле не будет учитываться при Сериализации
// Дефолтное значение для transient обьекта = null


