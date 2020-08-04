import org.apache.commons.io.FileUtils;
import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Какую папку скопировать?: ");
            String originalFolderInput = scanner.nextLine();
            System.out.print("Куда скопировать папку?: ");
            String copyFolderInput = scanner.nextLine();

            File originalFolder = new File(originalFolderInput);
            File copyFolder = new File(copyFolderInput);

            FileUtils.copyDirectoryToDirectory(originalFolder, copyFolder);

            System.out.println("Папка успешно скопирована");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
