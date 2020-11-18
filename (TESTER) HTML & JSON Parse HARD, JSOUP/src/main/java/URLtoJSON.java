import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.FileWriter;
import java.io.IOException;

public class URLtoJSON {

    private static JSONArray linesFinal = new JSONArray(); // Для линий
    private static JSONArray stationsArr = new JSONArray(); // Для станций
    private static JSONObject stationsFinal = new JSONObject();
    private static JSONArray connectionsFinal = new JSONArray(); // Для пересадок
    private static JSONObject finalFile = new JSONObject(); // Финальный файл
    private static String lineNumber = null; // Эта линия остается пока новая не выбрана
    private static String currentStation = null; // Эта станция остается пока новая не выбрана

    public static void createFile(String url, String pathToSave) {
        try {
            Document doc = Jsoup.connect(url).maxBodySize(0).get();
            Elements elements = doc.select("#metrodata").select("[data-line]").select("span");

            for (Element e : elements) {
                if (!e.select("[data-line]").isEmpty()) { // Выбор линий
                    parseLines(e);
                    continue;
                }
                if (!e.select(".name").text().isEmpty()) { // Выбор станций
                    parseStations(e);
                    continue;
                }
                if (!e.attr("title").isEmpty()) { // Выбор пересадок
                    parseConnections(e);
                }
            }
            stationsFinal.put(lineNumber, stationsArr); // Закидываем станции в последнюю линию

            removeDoublesInConnections();

            createJSONFile(pathToSave);

            System.out.println("JSON файл успешно создан!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void parseLines(Element e) {

        if (!stationsArr.isEmpty()) { // Защита от первой итерации
            stationsFinal.put(lineNumber, stationsArr); // Скидываем все накопившиеся линии в массив, перед тем как номер линии сменился
            stationsArr = new JSONArray(); // Создаем новый пустой массив станций когда линия менется
        }

        lineNumber = e.attr("data-line");
        String lineName = e.text();

        JSONObject lineJson = new JSONObject(); // Обьект одной линии
        lineJson.put("number", lineNumber);
        lineJson.put("name", lineName);
        linesFinal.add(lineJson); // Добавляем линию в массив линий
    }

    private static void parseStations(Element e) {

        currentStation = e.select(".name").text();
        stationsArr.add(currentStation);
    }

    private static void parseConnections(Element e) {

        String fromStationLine = lineNumber;
        String fromStationName = currentStation;

        String toStationLine = e.attr("class").replaceAll(".+ln-(.+)", "$1");
        String toStationName = e.attr("title").replaceAll(".+«(.+)».+", "$1");

        JSONObject fromJson = new JSONObject();
        JSONObject toJson = new JSONObject();

        fromJson.put("line", fromStationLine);
        fromJson.put("station", fromStationName);
        toJson.put("line", toStationLine);
        toJson.put("station", toStationName);

        JSONArray oneConnectionJson = new JSONArray();

        oneConnectionJson.add(fromJson);
        oneConnectionJson.add(toJson);

        connectionsFinal.add(oneConnectionJson);
    }

    private static void removeDoublesInConnections() {

        for (int i = 0; i < connectionsFinal.size(); i++) {
            JSONArray connection1 = (JSONArray) connectionsFinal.get(i);

            JSONObject from1 = (JSONObject) connection1.get(0);
            String fromLine1 = (String) from1.get("line");
            String fromStation1 = (String) from1.get("station");

            JSONObject to1 = (JSONObject) connection1.get(1);
            String toLine1 = (String) to1.get("line");
            String toStation1 = (String) to1.get("station");

            for (int j = i; j < connectionsFinal.size(); j++) {
                JSONArray connection2 = (JSONArray) connectionsFinal.get(j);

                JSONObject from2 = (JSONObject) connection2.get(0);
                String fromLine2 = (String) from2.get("line");
                String fromStation2 = (String) from2.get("station");

                JSONObject to2 = (JSONObject) connection2.get(1);
                String toLine2 = (String) to2.get("line");
                String toStation2 = (String) to2.get("station");

                if (fromLine1.equals(toLine2) && fromStation1.equals(toStation2)
                        && toLine1.equals(fromLine2) && toStation1.equals(fromStation2)) {
                    connectionsFinal.remove(j);
                }
            }
        }
    }

    private static void createJSONFile(String pathToSave) {

        finalFile.put("lines", linesFinal);
        finalFile.put("stations", stationsFinal);
        finalFile.put("connections", connectionsFinal);

        try (FileWriter file = new FileWriter(pathToSave)) {
            file.write(finalFile.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

