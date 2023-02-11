import company.*;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Company company = new Company(2000000);

        for (int i = 0; i < 180; i++) {
            company.hire(new Operator(50000 + Math.random() * 50000));
        }
        for (int i = 0; i < 80; i++) {
            company.hire(new Manager(50000 + Math.random() * 50000));
        }
        for (int i = 0; i < 10; i++) {
            company.hire(new TopManager(50000 + Math.random() * 50000, company));
        }

        System.out.println("Кол-во сотрудников: " + company.getEmployees().size());
        System.out.println("ЗП сотрудников:");
        printSalaries(company);

        System.out.println("========================================================");
        ArrayList<Employee> employeesFire = new ArrayList<>();
        int counter = 0;
        for (Employee employee :
                company.getEmployees()) {
            if (counter % 2 == 0) {
                employeesFire.add(employee);
            }
            counter++;

        }
        for (Employee employee :
                employeesFire) {
            company.fire(employee);
        }

        System.out.println("Кол-во сотрудников: " + company.getEmployees().size());
        System.out.println("ЗП сотрудников:");
        printSalaries(company);
    }

    public static void printSalaries(Company company) {
        System.out.println("\tВысокие зарплаты:");
        for (Employee employee :
                company.getTopSalaryStuff(15)) {
            System.out.println("\t\t" + employee.getMonthSalary());
        }
        System.out.println("\tНизкие зарплаты:");
        for (Employee employee :
                company.getLowestSalaryStuff(30)) {
            System.out.println("\t\t" + employee.getMonthSalary());
        }
    }
}
