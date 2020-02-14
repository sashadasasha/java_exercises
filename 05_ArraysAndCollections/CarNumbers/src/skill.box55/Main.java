package skill.box55;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) throws IOException {
        ArrayList<String> letters = new ArrayList<>() {{
            add("С");
            add("М");
            add("Т");
            add("В");
            add("А");
            add("Р");
            add("О");
            add("Н");
            add("Е");
            add("У");
            add("Х");
            add("К");
        }};

        Collections.sort(letters);

        ArrayList<String> regions = new ArrayList<>();
        for (int i = 1; i < 200; i++) {
            regions.add(Integer.toString(i));
        }

        for (int i = 0; i < 9; i++) {
            regions.set(i, "0" + regions.get(i));
        }

        ArrayList<String> numbers = new ArrayList<>() {{
            add("111");
            add("222");
            add("333");
            add("444");
            add("555");
            add("666");
            add("777");
            add("888");
            add("999");
        }};

        ArrayList<String> generatedNumbers = new ArrayList<>();

        String genNum = "";
        for (int i = 0; i < letters.size(); i++) {
            genNum = "";
            genNum += letters.get(i);
            for (String number : numbers) {
                String genNumCopy1 = genNum;
                genNum += number;
                for (String letter2 : letters) {
                    String genNumCopy2 = genNum;
                    genNum += letter2;
                    for (String letter3 : letters) {
                        String genNumCopy3 = genNum;
                        genNum += letter3;
                        for (String region : regions) {
                            String genNumCopy = genNum;
                            genNum += region;
                            generatedNumbers.add(genNum);
                            genNum = genNumCopy;
                        }
                        genNum = genNumCopy3;
                    }
                    genNum = genNumCopy2;
                }
                genNum = genNumCopy1;
            }
        }
        System.out.println(generatedNumbers.size());
        //Пробовала и с такой сортировкой, и без нее. Не знаю, что будет считаться правильной сортировкой в данном случае
        // Collections.sort(generatedNumbers);
        HashSet<String> hashSet = new HashSet<>(generatedNumbers);
        TreeSet<String> treeSet = new TreeSet<>(generatedNumbers);


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String searchNum = br.readLine();

            System.out.print("Поиск перебором: ");
            long start = System.nanoTime();
            if (generatedNumbers.contains(searchNum)) {
                System.out.print("Номер найден ");
            } else {
                System.out.print("Номер не найден ");
            }
            long finish = System.nanoTime()-start;
            System.out.println(" Поиск занял " + finish + "нс");

            System.out.print("Бинарный поиск: ");
            start = System.nanoTime();
            if (Collections.binarySearch(generatedNumbers, searchNum) > 0) {
                System.out.print("Номер найден");
            } else {
                System.out.print("Номер не найден");
            }
            finish = System.nanoTime()-start;
            System.out.println(" Поиск занял " + finish + "нс");

            //HashSet
            System.out.print("Поиск в HashSet: ");
            start = System.nanoTime();
            if (hashSet.contains(searchNum)) {
                System.out.print("Номер найден");
            } else {
                System.out.print("Номер не найден");
            }
            finish = System.nanoTime()-start;
            System.out.println(" Поиск занял " + finish + "нс");

            //TreeSet
            System.out.print("Поиск в TreeSet: ");
            start = System.nanoTime();
            if (treeSet.contains(searchNum)) {
                System.out.print("Номер найден");
            } else {
                System.out.print("Номер не найден");
            }
            finish = System.nanoTime()-start;
            System.out.println(" Поиск занял " + finish + "нс");
        }

    }
}