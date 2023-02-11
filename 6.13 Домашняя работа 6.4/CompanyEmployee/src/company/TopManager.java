package company;

public class TopManager implements Employee {
    private final double companySalary;
    private final double salary;
    public TopManager(double salary, Company company) {
        this.salary = salary;
        companySalary = company.getIncome();
    }


    @Override
    public double getMonthSalary() {
        return Math.round((companySalary > 10000000 ? salary * 2.5 : salary) * 100.0) / 100.0;
    }
}
