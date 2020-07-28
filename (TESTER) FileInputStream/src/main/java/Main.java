import java.io.FileInputStream;

public class Main {

    public static void main(String[] args) {

        StringBuilder builder = new StringBuilder();

        try {
            FileInputStream is = new FileInputStream("data/Заметки.txt");

            for (; ; ) {
                int code = is.read();                            // Код символа
                if (code < 0) {
                    break;
                }
                char ch = (char) code;                           // Преобразуем код в char
                builder.append(ch);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(builder);
    }
}
