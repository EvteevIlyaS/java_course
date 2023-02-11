import java.util.HashSet;
import java.util.Scanner;

public class Main {
    static HashSet<String> mailBox = new HashSet<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            String[] command = scanner.nextLine().trim().split(" ");

            switch (command[0]) {
                case "LIST" -> showMail();
                case "ADD" -> addMail(command[1]);
                case "END" -> {
                    return;
                }
                default -> System.out.println("Try again");
            }
        }
    }

    public static void addMail(String mail) {
        if (mail.matches("[\\w-]+@[a-zA-Z_]+?\\.[a-zA-Z]{2,6}")) {
            mailBox.add(mail);
        }
    }

    public static void showMail() {
        for (String mail :
                mailBox) {
            System.out.println(mail);
        }
    }
}
