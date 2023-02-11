package company;

public class Manager implements Employee{
    private final double earnedCompanySalary;
    private final double salary;
    public Manager(double salary) {
        this.salary = salary;
        earnedCompanySalary = 115000 + Math.random() * 25000;
    }

    @Override
    public double getMonthSalary() {
        return Math.round((salary + earnedCompanySalary * 0.05) * 100.0) / 100.0;
    }
}
