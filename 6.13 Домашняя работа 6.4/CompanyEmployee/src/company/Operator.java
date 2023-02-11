package company;

public class Operator implements Employee {
    private final double salary;

    public Operator(double salary) {
        this.salary = salary;
    }

    @Override
    public double getMonthSalary() {
        return Math.round(salary * 100.0) / 100.0;
    }
}
