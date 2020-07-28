
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, ParseException {

        String dataFile = "src/main/resources/map.json";

        List<String> listOfLines = Files.readAllLines(Paths.get(dataFile));        // List <String>
        System.out.println("readAllLines - " + listOfLines);

        StringBuilder builder = new StringBuilder();
        listOfLines.forEach(line -> builder.append(line));                         // String
        System.out.println("String (StringBuilder) - " + builder);

        JSONParser parser = new JSONParser();

        JSONObject fullFile = (JSONObject) parser.parse(builder.toString()); // JSONObject
        System.out.println("JSONObject parsed - " + fullFile); // Здесь можно get по лайнам и названиям типа stations

        JSONArray linesArray = (JSONArray) fullFile.get("lines");            // JSONArray
        System.out.println("Array of \"lines\" - " + linesArray); // Здесь можно искать по индексу


        JSONObject firstStation = (JSONObject) linesArray.get(0); // Взять инфу только по одной станции на индексе 0
        System.out.println(((Long) firstStation.get("number")).intValue()); // взять инфу по ключу number, формат будет object
        System.out.println((String) firstStation.get("name")); // взять инфу по ключу name, формат будет object
        System.out.println((String) firstStation.get("color")); // взять инфу по ключу color, формат будет object
        //jsonObject.get() - всегда возвращает Object и его нужно кастить

    }
}
