package skill.box6_4;

public class Manager implements Employee {

    private String name;
    private double fixSalary;
    private Company company;
    private final double bonusTax = 0.05;
    //Рандомная величина заработанных в компании денег
    private int bonusCount = (int)(Math.random() * ((500000 - 10000) + 1)) + 10000;
    public Manager (String name, double fixSalary) {
        this.name = name;
        this.fixSalary = fixSalary;
    }

    @Override
    public double getMonthSalary() {
        return fixSalary + bonusCount*bonusTax;
    }

    @Override
    public void setCompany(Company company) {
        this.company = company;
    }
}
