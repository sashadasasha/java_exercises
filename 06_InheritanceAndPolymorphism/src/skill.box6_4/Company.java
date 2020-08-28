package skill.box6_4;

import com.sun.source.tree.Tree;

import java.util.*;

public class Company {
    private String name;
    private double income;
    private ArrayList<Employee> employees = new ArrayList<>();

    public Company(String name, double income) {
        this.name = name;
        this.income = income;
        System.out.println("Компания создана");
    }

    public void hire(Employee employee) {
        employees.add(employee);
        System.out.println("Сотрудник принят!");
        employee.setCompany(this);
    }

    public void hireAll(ArrayList<Employee> employees) {
        for (Employee empl : employees) {
            hire(empl);
        }
    }

    public void fire(Employee employee) {
        if (employees.contains(employee)) {
            employees.remove(employee);
            System.out.println("Сотрудник уволен");
        }
    }

    public double getIncome() {
        return this.income;
    }

    public void getTopSalaryStaff(int count) {
        if (count > employees.size()) {
            System.out.println("Нет такого количества сотрудников");
        } else if (count > 0) {
            employees.sort(Collections.reverseOrder((empl1, empl2) -> {
                return Double.compare(empl1.getMonthSalary(), empl2.getMonthSalary());
            }));
            for (Employee empl : employees.subList(0, count - 1)) {
                System.out.println(empl.getMonthSalary());
            }
        } else {
            System.out.println("Введите корректное количество сотрудников");
        }
    }

    public void getLowestSalaryStaff(int count) {
        if (count > employees.size()) {
            System.out.println("Нет такого количества сотрудников");
        } else if (count > 0) {
            employees.sort((empl1, empl2) -> {
                return Double.compare(empl1.getMonthSalary(), empl2.getMonthSalary());
            });
            for (Employee empl : employees.subList(0, count - 1)) {
                System.out.println(empl.getMonthSalary());
            }
        } else {
            System.out.println("Введите корректное количество сотрудников");
        }
    }

    public ArrayList<Employee> getEmployees () {
        return this.employees;
    }
}
