package skill.box6_4;

public class Operator implements Employee{
    private String name;
    private double fixSalary;
    private Company company;
    public Operator (String name, double fixSalary) {
        this.name = name;
        this.fixSalary = fixSalary;
    }


    @Override
    public double getMonthSalary() {
        return this.fixSalary;
    }

    @Override
    public void setCompany(Company company) {
        this.company = company;
    }
}
