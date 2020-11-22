import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Tester {

    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("src/text.txt");

        PrintWriter pw = new PrintWriter(file);

        pw.write("Hello bitch");
        pw.flush();
        pw.close();
    }
}
