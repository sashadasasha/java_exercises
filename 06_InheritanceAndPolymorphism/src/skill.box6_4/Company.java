package skill.box6_4;

import com.sun.source.tree.Tree;

import java.util.*;

public class Company {
    private String name;
    private double income;
    private ArrayList<Employee> employees = new ArrayList<>();
    private int countEmployees;

    public Company (String name, double income) {
        this.name = name;
        this.income = income;
        this.countEmployees = 0;
        System.out.println("Компания создана");
    }

    public void hire(Employee employee) {
        employees.add(employee);
        System.out.println("Сотрудник принят!");
        employee.setCompany(this);
        this.countEmployees ++;
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
            this.countEmployees --;
        }
    }

    public double getIncome() {
        return this.income;
    }

    public int getCountEmployees() {
        return this.countEmployees;
    }

    public void getTopSalaryStaff(int count) {
        if(count > 0) {
            TreeSet<Double> topSalaries = new TreeSet<>();
            for (Employee empl: employees) {
                topSalaries.add(empl.getMonthSalary());
            }
            for (int i = 0; i < count; i ++) {
                System.out.println(topSalaries.last());
                topSalaries.remove(topSalaries.last());
            }
        } else if (count > countEmployees){
            System.out.println("Нет такого количества сотрудников");
        } else {
            System.out.println("Введите корректное количество сотрудников");
        }
    }

    public void getLowestSalaryStaff(int count) {
        if(count > 0) {
            TreeSet<Double> topSalaries = new TreeSet<>();
            for (Employee empl: employees) {
                topSalaries.add(empl.getMonthSalary());
            }
            for (int i = 0; i < count; i ++) {
                System.out.println(topSalaries.first());
                topSalaries.remove(topSalaries.first());
            }
        } else if (count > countEmployees){
            System.out.println("Нет такого количества сотрудников");
        } else {
            System.out.println("Введите корректное количество сотрудников");
        }
    }

    public ArrayList<Employee> getEmployees() {
        return this.employees;
    }
}
