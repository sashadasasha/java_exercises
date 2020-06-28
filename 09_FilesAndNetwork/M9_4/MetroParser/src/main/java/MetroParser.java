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

        writeToJson(listOfLines, listOfStations, pathToFile);

    }

    public static void writeToJson(List<Line> lines, List<Station> stations, String pathToFile) throws IOException {
        Map<String, List> jsonMap = new HashMap<>();
        jsonMap.put("lines", lines);
        jsonMap.put("stations", stations);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.
                writerWithDefaultPrettyPrinter().
                writeValue(new File(pathToFile), jsonMap);
    }

    public static void readFromJson(String pathToFile) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Metro metro = objectMapper.readValue(new File(pathToFile), Metro.class);
        metro.getNumberOfStationsOnLine();
    }
}
