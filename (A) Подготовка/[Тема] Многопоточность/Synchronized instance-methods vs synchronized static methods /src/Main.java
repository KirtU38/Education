public class Main {

    public static void main(String[] args) {

        Test test = new Test();

        new Thread(() -> {
            try {
                Test.print();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                test.wtf();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        // synchronized instance-метод (не static) блокирует все другие synchronized методы (как бы блокирует обьект)
        // не synchronized instance-методы будут работать
        // synchronized/non-synchronized static методы будут работать

        // synchronized static метод блокирует все другие synchronized static методы
        // не synchronized static-методы будут работать
        // synchronized/non-synchronized instance-методы будут работать
    }
}









