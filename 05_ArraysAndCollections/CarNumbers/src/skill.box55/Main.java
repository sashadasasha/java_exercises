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
            if (i < 10) {
                regions.add("0" + i);
                continue;
            }
            regions.add(Integer.toString(i));
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

        ArrayList<String> numbersForIdenticalLetters = new ArrayList<>();

        for (int i = 1; i < 1000; i ++) {
            if (i<10) {
                numbersForIdenticalLetters.add("00" + i);
                continue;
            }
            if (i < 100) {
                numbersForIdenticalLetters.add("0" + i);
                continue;
            }
            if (i == 111 || i == 222 || i == 333 || i == 444 || i == 555 || i == 666 || i == 777 || i == 888 || i == 999 ) {
                continue;
            }
            numbersForIdenticalLetters.add(Integer.toString(i));

        }


        ArrayList<String> generatedNumbers = new ArrayList<>();

        for (String letter: letters) {
            for (String number: numbers) {
                for (String letter2: letters) {
                    for (String letter3: letters) {
                        for (String region: regions) {
                            generatedNumbers.add(String.format("%s%s%s%s%s", letter, number, letter2, letter3, region));
                            if (letter.equals(letter2) && letter.equals(letter3)) {
                                for (String num : numbersForIdenticalLetters) {
                                    generatedNumbers.add(String.format("%s%s%s%s%s", letter, num, letter2, letter3, region));
                                }
                            }
                        }
                    }
                }
            }
        }

        System.out.println(generatedNumbers.size());

        //Да, вот эта сортировка нужна, без нее некорректно ищет
        Collections.sort(generatedNumbers);
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