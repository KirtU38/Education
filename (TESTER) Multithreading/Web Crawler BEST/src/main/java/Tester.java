import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Tester {

    StringBuilder tabs = new StringBuilder();

    public static void main(String[] args) throws IOException {

        List<String> lines = Files.readAllLines(Path.of("src/asdqwebg.txt"));

        Collections.sort(lines); // test.txt

        for (int i = 0; i < lines.size(); i++) {
            String[] split = lines.get(i).split("/"); // [https:, , jsoup.org, cookbook]
            String anchor = split[split.length - 1]; // cookbook

            for (int j = 0; j < lines.size(); j++) {
                if(lines.get(j).contains(anchor)){

                }
            }
        }

    }
}
