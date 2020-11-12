public class Main {
    public static void main(String[] args) {

        for (int i = 0; i < 4; i++) {                   // Атомарная переменная
            new Thread(()->{
                for (int j = 0; j < 100000; j++) {
                    AtomicValue.increment();
                }
                System.out.println(AtomicValue.getX());
            }).start();
        }

        /*for (int i = 0; i < 4; i++) {                   // Обычная переменная
            new Thread(()->{
                for (int j = 0; j < 100000; j++) {
                    AtomicValue.increment();
                }
                System.out.println(AtomicValue.getX());
            }).start();
        }*/
    }
}
