package skill.box6_4;

public class TopManager implements Employee {
    private String name;
    private double fixSalary;
    private Company company;
    private final double bonusTax = 1.5;
    public TopManager (String name, double fixSalary) {
        this.name = name;
        this.fixSalary = fixSalary;
    }

    @Override
    public double getMonthSalary() {
        if (company.getIncome() > 10000000.0) {
            return fixSalary + fixSalary*bonusTax;
        } else {
            return fixSalary;
        }
    }

    @Override
    public void setCompany(Company company) {
        this.company = company;
    }
}
