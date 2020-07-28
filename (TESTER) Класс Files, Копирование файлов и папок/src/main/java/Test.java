import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class Test {

    public static void main(String[] args) {

        try {

            File originalFile = new File("/users/egor/downloads/Pepel druga");
            String copyFolder = "/users/egor/desktop/";
            //copyFolder += originalFile.getName();
            File copyPath = new File(copyFolder + "/" + originalFile.getName());
            /*Files.copy(originalFile.toPath(), copyPath.toPath());*/
            copyPath.mkdir();
            System.out.println(copyPath);


        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
