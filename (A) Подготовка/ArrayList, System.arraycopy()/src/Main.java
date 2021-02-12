import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        long start;

        // ArrayList с заданным capacity немного быстрее, тк ему не нужно делать resize,
        // но обычный int[] все равно в разы быстрее из-за примитивного типа int
        // Однако Integer[] против List<Integer> с заданным capacity - практически одинаковы
        start = System.currentTimeMillis();
        List<Integer> listWithCapacity = new ArrayList<>(10000005);
        for (int i = 0; i < 10000000; i++) {
            listWithCapacity.add(i);
        }
        System.out.println(System.currentTimeMillis() - start + " ms ArrayList with capacity");

        start = System.currentTimeMillis();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10000000; i++) {
            list.add(i);
        }
        System.out.println(System.currentTimeMillis() - start + " ms ArrayList empty");

        start = System.currentTimeMillis();
        Integer[] integerArray = new Integer[10000005];
        for (int i = 0; i < 10000000; i++) {
            integerArray[i] = i;
        }
        System.out.println(System.currentTimeMillis() - start + " ms Integer[]");

        start = System.currentTimeMillis();
        int[] intArray = new int[10000005];
        for (int i = 0; i < 10000000; i++) {
            intArray[i] = i;
        }
        System.out.println(System.currentTimeMillis() - start + " ms int[]");

    }
}
// Экстендит AbstractList, имплементит

// ArrayList под копотом по сути обычный array[], но который может автоматически расширятся

// Начальный capacity = 10, однако если элементов больше, то происходит расширение методом System.copyOf()
// Увеличивается в 2 раза (вроде как) 50%
// Очень важно задавать ему initial capacity чтобы ему не пришлось постоянно перекопировать элементы и расти

// Если вызвать метод add() по индексу например в центр, то происходит перекопирование правой части листа
// методом System.arraycopy(), что соответственно очень замедляет

// Позволяет хранить null и get() по null тоже работает нормально








