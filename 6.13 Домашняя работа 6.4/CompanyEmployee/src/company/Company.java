package company;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Company {
    private final TreeSet<Employee> employees = new TreeSet<>(new EmployeeComparator());
    private final double income;

    public Company(double income) {
        this.income = income;
    }

    public void hire(Employee newEmployee) {
        employees.add(newEmployee);
    }

    public void hireAll(ArrayList<Employee> newEmployees) {
        employees.addAll(newEmployees);
    }

    public void fire(Employee employee) {
        employees.remove(employee);
    }

    public double getIncome() {
        return income;
    }

    public List<Employee> getTopSalaryStuff(int count) {
        List<Employee> topStuff = new ArrayList<>(employees);
        return topStuff.subList(0, count);
    }

    public List<Employee> getLowestSalaryStuff(int count) {
        List<Employee> topStuff = new ArrayList<>(employees);
        return topStuff.subList(topStuff.size() - count, topStuff.size());
    }

    public TreeSet<Employee> getEmployees() {
        return employees;
    }
}
