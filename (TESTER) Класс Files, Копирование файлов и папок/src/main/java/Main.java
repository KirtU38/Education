import java.io.*;
import java.nio.file.Files;
import java.util.Scanner;

public class Main {

    private static Scanner scanner;

    public static void main(String[] args) {

        try {

            scanner = new Scanner(System.in);

            System.out.print("Какую папку скопировать?: ");
            String originalFolderInput = scanner.nextLine();
            System.out.print("Куда скопировать папку?: ");
            String copyFolderInput = scanner.nextLine();

            File originalFolder = new File(originalFolderInput);
            File copyFolder = new File(copyFolderInput + "/" + originalFolder.getName());

            copyFolder.mkdir();

            makeCopy(originalFolder, copyFolder);

            System.out.println("Папка успешно скопирована");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void makeCopy(File from, File to) {
        try {

            File[] files = from.listFiles();

            for (File file : files) {
                if (file.isDirectory()) {
                    expandAndCopyFolder(file, to);
                    continue;
                }
                copyFiles(file, to);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void expandAndCopyFolder(File originalFolder, File copyFolder) {
        try {

            File newFolder = new File(copyFolder + "/" + originalFolder.getName());
            newFolder.mkdir();
            makeCopy(originalFolder, newFolder);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void copyFiles(File from, File to) {
        try {

            File copy = new File(to + "/" + from.getName());
            Files.copy(from.toPath(), copy.toPath());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
