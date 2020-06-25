import com.google.gson.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MetroParser {
    public static void main(String[] args) throws IOException {
        getInfoFromHTML();
        readFromJson("F:\\SkillBox\\java_basics\\09_FilesAndNetwork\\M9_4\\MetroParser\\data\\metro.json");
    }

    public static void getInfoFromHTML() throws IOException {
        List<Line> listOfLines = new ArrayList<>();
        List<Station> listOfStations = new ArrayList<>();
        Document doc = Jsoup.connect("https://www.moscowmap.ru/metro.html#lines").maxBodySize(0).get();
        Elements namesOfLines = doc.getElementsByClass("js-metro-line");
        namesOfLines.forEach(el -> {
            listOfLines.add(new Line(el.text(), el.attr("data-line")));
        });

        Elements namesOfStations = doc.getElementsByClass("js-metro-stations");
        namesOfStations.forEach(el -> {
            el.children().forEach(element -> {
                listOfStations.add(new Station(element.getElementsByClass("name").text(), el.attr("data-line")));
            });
        });

        writeToJson(listOfLines, listOfStations);

    }

    public static void writeToJson(List<Line> lines, List<Station> stations) throws IOException {
        Map<String, List> jsonMap = new HashMap<>();
        jsonMap.put("Lines", lines);
        jsonMap.put("Stations", stations);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(jsonMap);
        Files.writeString(Paths.get("F:\\SkillBox\\java_basics\\09_FilesAndNetwork\\M9_4\\MetroParser\\data\\metro.json"), json);
    }

    public static void readFromJson(String pathToFile) throws IOException {
        byte[] bytesFromJson = Files.readAllBytes(Paths.get(pathToFile));
        JsonObject jsonObject = JsonParser.parseString(new String(bytesFromJson)).getAsJsonObject();
        JsonArray Lines = jsonObject.get("Lines").getAsJsonArray();
        JsonArray Stations = jsonObject.get("Stations").getAsJsonArray();
        Map<String, Integer> countStationsOnLine = new HashMap<>();
        Lines.forEach(el -> {
           String numberOfLine = el.getAsJsonObject().get("number").toString();
           countStationsOnLine.put(el.getAsJsonObject().get("name").toString(), 0);
           Stations.forEach(station -> {
               if (station.getAsJsonObject().get("numberOfLine").toString().equals(numberOfLine)) {
                   countStationsOnLine.replace(el.getAsJsonObject().get("name").toString(),
                           countStationsOnLine.get(el.getAsJsonObject().get("name").toString()) + 1);
               }
           });
        });
        countStationsOnLine.entrySet().forEach(element -> {
            System.out.println("Линия" + element.getKey() +": количество станций - " + element.getValue());
        });
    }
}
