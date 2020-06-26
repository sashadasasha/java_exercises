import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

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

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.
                writerWithDefaultPrettyPrinter().
                writeValue(new File("F:\\SkillBox\\java_basics\\09_FilesAndNetwork\\M9_4\\MetroParser\\data\\metro.json"), jsonMap);
    }

    public static void readFromJson(String pathToFile) throws IOException {
        byte[] bytesFromJson = Files.readAllBytes(Paths.get(pathToFile));
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(new String(bytesFromJson));
        JsonNode lines = jsonNode.get("Lines");
        JsonNode stations = jsonNode.get("Stations");
        Map<String, Integer> countStationsOnLine = new HashMap<>();
        lines.forEach(line -> {
            String numberOfLine = line.get("number").toString();
            countStationsOnLine.put(line.get("name").toString(), 0);
            stations.forEach(station -> {
                if (station.get("numberOfLine").toString().equals(numberOfLine)) {
                    countStationsOnLine.replace(line.get("name").toString(), countStationsOnLine.get(line.get("name").toString()) + 1);
                }
            });
        });
        countStationsOnLine.forEach((key, value) -> System.out.println("Линия" + key + ": количество станций - " + value));
    }
}
