import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.nio.file.StandardCopyOption.*;

public class Main {
    public static Map<String, Double> stations = new HashMap<>();
    public static ArrayList<Line> lines = new ArrayList<>();
    public static Path jsonStations;

    public static void main(String[] args) throws IOException {
        Element stationsTable = getStationsTable();
        generateJsonFile(stationsTable);

    }

    public static Element getStationsTable() throws IOException {
        Document doc = Jsoup.connect("https://ru.wikipedia.org/wiki/%D0%A1%D0%BF%D0%B8%D1%81%D0%BE%D0%BA_%D1%81%D1%82%D0%B0%D0%BD%D1%86%D0%B8%D0%B9_%D0%9C%D0%BE%D1%81%D0%BA%D0%BE%D0%B2%D1%81%D0%BA%D0%BE%D0%B3%D0%BE_%D0%BC%D0%B5%D1%82%D1%80%D0%BE%D0%BF%D0%BE%D0%BB%D0%B8%D1%82%D0%B5%D0%BD%D0%B0").maxBodySize(0).get();
        Elements infoTable = doc.select("table");
        return infoTable.get(3);
    }

    public static void generateJsonFile(Element table) throws IOException {
        //jsonStations = Files.createFile(Paths.get("/media/sasha/3c0c1705-6766-4724-8a72-b46458c5d3401/Документы/SkillboxGitLab/java_basics/09_FilesAndNetwork/M9_5/stations.json"));
        Elements rows = table.select("tr");
        rows.forEach(row -> {
                Elements columns = row.select("td");
                for (int i = 0; i < columns.size(); i ++) {
                    Element aboutStation = columns.get(0);
                    //aboutStation.children().get(1).attr("title") - название станции
                    //aboutStation.attr("data-sort-value") - номер станции
                    //Цвет
                    System.out.println(aboutStation.attr("style"));
                }
        });
    }
}