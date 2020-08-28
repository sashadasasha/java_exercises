package skill.box6_4;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        //Создаем компанию
        Company gazprom = new Company("Газпром", 25000000);

        //Создаем 180 операторов
        ArrayList<Employee> listOfOperators = new ArrayList<>();
        for (int i = 0; i < 180; i ++) {
            listOfOperators.add(new Operator(nameGenerator(), salaryGenerator(40000, 15000)));
        }

        //Создаем 80 менеджеров
        ArrayList<Employee> listOfManagers = new ArrayList<>();
        for (int i = 0; i < 80; i ++) {
            listOfManagers.add(new Manager(nameGenerator(), salaryGenerator(100000, 50000)));
        }

        //Создаем 10 топ-менеджеров
        ArrayList<Employee> listOfTops = new ArrayList<>();
        for (int i = 0; i < 10; i ++) {
            listOfManagers.add(new Manager(nameGenerator(), salaryGenerator(300000, 120000)));
        }

        //Нанимаем
        gazprom.hireAll(listOfOperators);
        gazprom.hireAll(listOfManagers);
        gazprom.hireAll(listOfTops);

        System.out.println("---------------------");
        //Печатает топ-зарплаты
        gazprom.getTopSalaryStaff(10);

        System.out.println("---------------------");
        //Печатает самые маленькие зарплаты
        gazprom.getLowestSalaryStaff(30);

        for (int i = 0; i < 50; i ++) {
            gazprom.fire(gazprom.getEmployees().get(whoFiredGenerator(gazprom)));
        }

        System.out.println(gazprom.getEmployees().size());
        System.out.println("---------------------");
        gazprom.getTopSalaryStaff(10);
        System.out.println("---------------------");
        gazprom.getLowestSalaryStaff(30);


    }

    //Генератор случайной зарплаты для оператора
    public static int salaryGenerator (int max, int min) {
        return (int)(Math.random() * ((max - min) + 1)) + min;
    }

    //Генератор фамилий сотруников
    public static String nameGenerator() {
        String[] names = {"Иванов", "Петров", "Сидоров", "Куваев", "Смирнов", "Тарасов", "Подоплелов", "Прилипалов", "Сметанин"};
        return names[(int)(Math.random() * ((names.length - 1) + 1))];
    }

    //Генератор увольнения случайного сотрудника
    public static int whoFiredGenerator(Company company) {
        int count = company.getEmployees().size();
        return (int)(Math.random() * ((count) + 1));
    }
}
