package skill.box93;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    private static Path path;
    private static final Pattern pattern = Pattern.compile("MCC\\d{1,4}");
    private static Matcher matcher;
    private static List<OutcomeInfo> listOfOutcomes;

    public static void main(String[] args) throws IOException {
        path = Paths.get(args[0]);
        listOfOutcomes = readFile(path);
        HashMap<String, Double> mapOfOutcome= new HashMap<>();

        for (OutcomeInfo i : listOfOutcomes) {
            mapOfOutcome.computeIfPresent(i.getCodeStatus(), (key, value) -> value + i.getSum());
            mapOfOutcome.putIfAbsent(i.getCodeStatus(), i.getSum());
        }

        for (Map.Entry<String, Double> pairs : mapOfOutcome.entrySet()) {
            System.out.printf("По операциям, относящихся к категории %s потрачено %.2f%n", pairs.getKey(), pairs.getValue());
        }
    }

    private static List<OutcomeInfo> readFile(Path path) throws IOException {
        List<String> lines = Files.readAllLines(path);
        ArrayList<String[]> list = new ArrayList<>();
        ArrayList<OutcomeInfo> outcomesList = new ArrayList<>();
        for (String line: lines) {
            list.add(line.split(","));
        }
        list.remove(0);

        double allIncome = 0;
        double allOutcome = 0;
        double sumOutOfItem = 0;
        for (String[] strings : list) {
            if (strings.length == 9) {
                sumOutOfItem = Double.parseDouble(strings[7].replace("\"", "") + "." + strings[8].replace("\"", ""));
                allOutcome += sumOutOfItem;
            } else {
                sumOutOfItem = Double.parseDouble(strings[7]);
                allOutcome += sumOutOfItem;
            }
            allIncome += Double.parseDouble(strings[6]);
            if (sumOutOfItem > 0) {
                String operations = strings[5];
                Matcher m = pattern.matcher(operations);
                if (m.find()) {
                    String code = m.group();
                    code = code.replace("MCC", "");
                    outcomesList.add(new OutcomeInfo(sumOutOfItem, Integer.parseInt(code), checkCodeStatuc(code)));

                }
                sumOutOfItem = 0;
            }
        }

        System.out.printf("Сумма всех приходных операций - %s%n" +
                        "Сумма всех расходных операций - %s%n ", allIncome, allOutcome);
        return outcomesList;
    }

    private static String checkCodeStatuc(String code) throws IOException {
        Document doc = Jsoup.connect("https://mcc-codes.ru/code/" + code).get();
        Elements status = doc.select("h1");
        StringBuffer sb = new StringBuffer(status.text());
        sb.delete(0, 10);
        return sb.toString();
    }
}

