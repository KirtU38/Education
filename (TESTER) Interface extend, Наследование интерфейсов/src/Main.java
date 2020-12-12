import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<ParentInterface> list = new ArrayList<>();
        list.add(new Test1());
        list.add(new Test2());

        list.get(0).print();
        list.get(1).print();

        List<ChildInterface> listChild = new ArrayList<>();
        //listChild.add(new Test1());         // Этот уже нельзя добавить, т.к он выше в дереве наследования(он имплементит интерфейс-родитель)
                                              // и не имеет метода shout(), который есть только у ChildInterface
        listChild.add(new Test2());

        listChild.get(0).print();
        listChild.get(0).shout();
    }
}
// Интерфейсы можно наследовать
