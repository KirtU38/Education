import java.util.Arrays;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

        // У двух одинаковых Стрингов будет одинаковый Хэшкод
        String d = "asqweqwe-csk1283172";
        String a = "asqweqwe-csk1283172";
        System.out.println(d.hashCode() + " String");
        System.out.println(a.hashCode() + " String");

        // По дефолту у класса Object хэш генерируется по местоположению в памяти(числовое значение положения в памяти)
        // Если мы переписали хэш у класса Human по полям name и age, то они будут равны хэшу массива класса Object
        // со значениями {"Egor", 25}
        Human egor = new Human("Egor", 25);
        Human copy = egor;
        Human egor1 = new Human("Egor", 25);
        int object = Arrays.hashCode(new Object[]{"Egor", 25});
        System.out.println(egor.hashCode() + " egor");
        System.out.println(copy.hashCode() + " egor копия");
        System.out.println(egor1.hashCode() + " egor1 новый обьект с такими же полями");
        System.out.println(object + " new Object[]{\"Egor\", 25}");

        Byte byteNumber = 126;
        System.out.println();

        // Мапа с заданным capacity будет быстрее
        long start = System.currentTimeMillis();
        HashMap<Integer, Integer> map1 = new HashMap<>(4000010);
        for (int i = 0; i < 4000000; i++) {
            map1.put(i,i);
        }
        System.out.println(map1.size());
        System.out.println(System.currentTimeMillis() - start + " ms");
        map1.clear();

        start = System.currentTimeMillis();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < 4000000; i++) {
            map.put(i,i);
        }
        System.out.println(map.size());
        System.out.println(System.currentTimeMillis() - start + " ms");
        map.clear();




    }
}
// Сам хэш это нативный метод, значит он написан на другом языке, в Джаве хэш взять с C++

// У двух одинаковых обьектов по методу equals() обязаны быть одинаковые хэши, но два обьекта с одинаковым хэшем
// могут быть и разными на самом деле

// Хэш генерируется в диапазоне Integer(~4млрд значений)
// Хэш может быть отрицательным числом

// В HashMap все элементы хранятся в бакетах(бинах) по хэшу, однако если в HashMap попадает обьект с уже существующим в
// мапе хэшом(происходит коллизия, collision), и он не проходит по методу equals(), то он добавляется в этот же бакет,
// и элементы в бакете хранятся по типу LinkedList, однако если элементов в бакете становится больше 8, то

// Initial capacity или начальный размер составляет 16 бакетов (16 элементов можно сказать), однако он ресайзится не
// после 16 элементов, а когда достигнут load factor который составляет по дефолту 75%, то если по дефолту HashMap
// ресайзится после 12 элементов
// HashMap ресайзится в 2 раза, то есть если было 16, потом будет 32 и так далее
// При ресайзе происходит rehash, что очень плохо, тк это занимает время, поэтому нужно заранее задавать Мапе размер,
// чтобы происходило минимальное количество rehash`ей
// Испольховане многих ключей с одинаковых hashcode очень сильно замедляет Мапу

// Отличие HashMap от HashTable
// HashMap допускает null ключи и значения и она не synchronized, в то время как у HashTable все методы synchronized
// и он не допускает null`ы вообще








