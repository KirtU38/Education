import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class Tester1 {

    public static void main(String[] args) {

        try {

            File originalFolder = new File("/users/egor/downloads/Pepel druga");

            File[] files = originalFolder.listFiles();

            String copyFolderStr = "/users/egor/desktop/" + originalFolder.getName();

            File copyFolder = new File(copyFolderStr);

            Files.copy(originalFolder.toPath(), copyFolder.toPath());

            for (File file : files) {

                String copyFileStr = copyFolderStr + "/" + file.getName();

                System.out.println(copyFileStr);

                File copyFile = new File(copyFileStr);

                Files.copy(file.toPath(), copyFile.toPath());

            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
