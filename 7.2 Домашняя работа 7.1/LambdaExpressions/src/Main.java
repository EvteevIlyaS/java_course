import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class Main {
    private static String staffFile = "data/staff.txt";
    private static String dateFormat = "dd.MM.yyyy";

    public static void main(String[] args) {
        Airport airport = Airport.getInstance();
//        System.out.println(Stream.concat(airport.getTerminals().stream().map(Terminal::getFlights)));
        List<Flight> flights = new ArrayList<>();
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
        airport.getTerminals().forEach(o -> flights.addAll(o.getFlights()));
        flights.stream().filter(o -> {
            if (o.getType() == Flight.Type.DEPARTURE) {
                Date departureDate = o.getDate();
                Date date = new Date();
                double diff = (double) (departureDate.getTime() - date.getTime()) / 1000 / 3600;
                return 0 <= diff && diff < 2;
//                cal.setTime(departureDate);
//                int hoursDep = cal.get(Calendar.HOUR_OF_DAY);
//                cal.setTime(date);
//                int hoursCurr = cal.get(Calendar.HOUR_OF_DAY);
//                int diffHours = hoursDep - hoursCurr;
//                return 0 <= diffHours && diffHours < 2;
            }
            return false;
        }).map(o -> o.getDate() + " / " + o.getAircraft().getModel()).toList().forEach(System.out::println);

//        ArrayList<Employee> staff = loadStaffFromFile();
//        Collections.sort(staff, (o1, o2) -> {
//            int comp = o1.getSalary().compareTo(o2.getSalary());
//            if (comp != 0) {
//                return comp;
//            }
//            return o1.getName().compareTo(o2.getName());
//        });
//        Collections.sort(staff, Comparator.comparing(Employee::getSalary).thenComparing(Employee::getName));
//
//        for (Employee employee:
//             staff) {
//            System.out.println(employee);
//        }
//        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
//        staff.stream().filter(employee -> {
//            Date date = employee.getWorkStart();
//            cal.setTime(date);
//            return cal.get(Calendar.YEAR) == 2017;
//        }).max(Comparator.comparing(Employee::getSalary)).ifPresent(System.out::println);

    }

    private static ArrayList<Employee> loadStaffFromFile() {
        ArrayList<Employee> staff = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(staffFile));
            for (String line : lines) {
                String[] fragments = line.split("\t");
                if (fragments.length != 3) {
                    System.out.println("Wrong line: " + line);
                    continue;
                }
                staff.add(new Employee(
                        fragments[0],
                        Integer.parseInt(fragments[1]),
                        (new SimpleDateFormat(dateFormat)).parse(fragments[2])
                ));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return staff;
    }
}