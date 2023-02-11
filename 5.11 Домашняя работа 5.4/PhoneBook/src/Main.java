import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static TreeMap<String, String> phoneBook = new TreeMap<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("Input command");
            String cmd = scanner.nextLine();
            if ("list".equals(cmd)) {
                list();
            } else {
                if (cmd.matches("8[0-9]{10}")) {
                    checkNumber(cmd);
                } else if (cmd.matches("[А-Я][а-я]+\s[А-Я][а-я]+")) {
                    checkName(cmd);
                } else {
                    System.out.println("Wrong command");
                }
            }
        }
    }

    static void list() {
        for (String key :
                phoneBook.keySet()) {
            System.out.println(key + " - " + phoneBook.get(key));
        }
    }

    static void checkNumber(String number) {
        if (phoneBook.containsKey(number)) {
            System.out.println(number + " - " + phoneBook.get(number));
        } else {
            System.out.println("Please, input name");
            phoneBook.put(number, scanner.nextLine());
        }
    }

    static void checkName(String name) {
        if (phoneBook.containsValue(name)) {
            for (String key :
                    phoneBook.keySet()) {
                String value = phoneBook.get(key);
                if (value.equals(name)) {
                    System.out.println(key + " - " + value);
                }
            }
        } else {
            System.out.println("Please, input number");
            phoneBook.put(scanner.nextLine(), name);
        }
    }
}
