import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final String url = "https://www.moscowmap.ru/metro.html#lines";
    private static final String pathToFile = "files/stations.json";

    public static void main(String[] args) {
        try {
            URLtoJSON.createFile(url, pathToFile);

            List<String> fileStrList = Files.readAllLines(Paths.get(pathToFile));
            StringBuilder builder = new StringBuilder();
            fileStrList.forEach(builder::append);
            JSONParser parser = new JSONParser();

            JSONObject fullFile = (JSONObject) parser.parse(builder.toString());
            JSONArray linesJson = (JSONArray) fullFile.get("lines");
            JSONObject stationsJson = (JSONObject) fullFile.get("stations");
            List<Line> linesList = new ArrayList<>();

            parseLines(linesJson, stationsJson, linesList);

            printLines(linesList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void parseLines(JSONArray linesJson,JSONObject stationsJson, List<Line> linesList){

        linesJson.forEach(l -> {
            List<Station> stations = new ArrayList<>();

            JSONObject lineJson = (JSONObject) l;

            String lineName = lineJson.get("name").toString();
            String lineNumber = lineJson.get("number").toString();

            JSONArray stationsOnTheLine = (JSONArray) stationsJson.get(lineNumber);

            stationsOnTheLine.forEach(s -> stations.add(new Station(lineNumber, s.toString())));

            linesList.add(new Line(lineName, lineNumber, stations));
        });
    }

    private static void printLines(List<Line> linesList){

        for (Line line : linesList){
            String lineNumber = line.getNumber();
            String lineName = line.getName();
            int numberOfStations = line.getStations().size();

            System.out.printf("Номер линии %s %s - количество станций: %d \n", lineNumber, lineName, numberOfStations);
        }
    }
}
