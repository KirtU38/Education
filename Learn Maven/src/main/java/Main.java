import java.util.ArrayList;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) {

        HashSet<Test> set = new HashSet<>();

        Test test = new Test();
        Test test1 = new Test();
        test1.id = 10;

        System.out.println(test.hashCode());
        System.out.println(test1.hashCode());

        set.add(test);
        set.add(test1);
        System.out.println(set.size());



    }
}

// Должен быть результать 10 раз по миллиону, однако без слова synchronized в методе потоки будут путаться так как
// получают доступ к одному Листу одновременно, однако с synchronized методом они будут получать доступ к методу
// по очереди (1 метод выполняется одновременно максимум 1 потоком)


// Если нас не пугает выполнение одного процесса(хоть и не совсем правильно) разными потоками, а пугают
// только ошибки с этим связанные, мы может сделать ПОТОКО-БЕЗОПАСНЫМ только БЛОК кода, а не весь метод
