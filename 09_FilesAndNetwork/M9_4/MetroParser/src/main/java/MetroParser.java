import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class MetroParser {

    private static String pathToFile = "F:\\SkillBox\\java_basics\\09_FilesAndNetwork\\M9_4\\MetroParser\\data\\metro.json";
    public static void main(String[] args) throws IOException {
        getInfoFromHTML(pathToFile);
        readFromJson(pathToFile);
    }

    public static void getInfoFromHTML(String pathToFile) throws IOException {
        List<Line> listOfLines = new ArrayList<>();
        Map<String, List<Station>> mapOfStations = new HashMap<>();
        Document doc = Jsoup.connect("https://www.moscowmap.ru/metro.html#lines").maxBodySize(0).get();
        Elements namesOfLines = doc.getElementsByClass("js-metro-line");
        Elements namesOfStations = doc.getElementsByClass("js-metro-stations");
        namesOfLines.forEach(line -> {
            listOfLines.add(new Line(line.text(), line.attr("data-line")));
            namesOfStations.forEach(station -> {
                station.children().forEach(stationOnLine -> {
                    if (line.attr("data-line").equals(station.attr("data-line"))) {
                        if (!mapOfStations.containsKey(line.text())) {
                            mapOfStations.put(line.text(), new ArrayList<Station>());
                        }
                        mapOfStations.get(line.text()).add(new Station(stationOnLine.getElementsByClass("name").text(), station.attr("data-line")));
                    }
                });
            });
        });

        Metro metro = new Metro(listOfLines, mapOfStations);
        writeToJson(metro, pathToFile);

    }

    public static void writeToJson(Metro metro, String pathToFile) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.
                writerWithDefaultPrettyPrinter().
                writeValue(new File(pathToFile), metro);
    }

    public static void readFromJson(String pathToFile) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Metro metro = objectMapper.readValue(new File(pathToFile), Metro.class);
        metro.getStationsOnLines().forEach((key, value) -> {
            System.out.println(key + " - количество станций - " + value.size());
        });
    }
}
