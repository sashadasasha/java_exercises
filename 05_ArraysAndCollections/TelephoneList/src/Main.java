import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {
        TreeMap<String, String> telephoneBook = new TreeMap<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Pattern patternNumber = Pattern.compile("\\d{3,10}"); //Для упрощения я не использую проверку на корректность ввода (пробелы, скобки и т.п.) и длину номера
        Pattern patternName = Pattern.compile("\\D{3,25}"); // C именем тоже просто проверка на символы
        Matcher matcherNumber;
        Matcher matcherName;

        while(true) {
            String userInput = reader.readLine();
            matcherName = patternName.matcher(userInput);
            matcherNumber = patternNumber.matcher(userInput);
            if (userInput.equals("LIST")) {
                printList(telephoneBook);
                continue;
            }
            if (matcherName.matches()) {
                if (telephoneBook.containsKey(userInput)) {
                    System.out.println("Имя " + userInput + " - номер: " + telephoneBook.get(userInput));
                } else {
                    System.out.println("Введите номер контакта");
                    String number = reader.readLine();
                    telephoneBook.put(userInput, number);
                }
            } else if (matcherNumber.matches()) {
                if (telephoneBook.containsValue(userInput)) { ;
                    printNumber(telephoneBook, userInput);
                } else {
                    System.out.println("Введите имя контакта");
                    String name = reader.readLine();
                    telephoneBook.put(name, userInput);
                }
            }
        }
    }

    public static void printList (Map<String, String> map) {
        for (Map.Entry<String, String> val: map.entrySet()) {
            System.out.println(val);
        }
    }

    public static void printNumber (Map<String, String> map, String numberForChecking) {
        for (String key : map.keySet()) {
            if (map.get(key).equals(numberForChecking)) {
                System.out.println(key + "=>" + map.get(key));
            }
        }
    }
}