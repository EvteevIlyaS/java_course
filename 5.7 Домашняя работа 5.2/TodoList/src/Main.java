import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> todoArray = new ArrayList<>();
        Pattern patternNumber = Pattern.compile("-?([0-9]|[1-9][0-9+])");

        while (true) {
            System.out.println("Введите команду:");
            String cmd = scanner.nextLine().trim();
            String[] splittedArray = cmd.split("\s");
            String keyWord = splittedArray[0];


            switch (keyWord) {
                case "LIST":
                    for (int i = 0; i < todoArray.size(); i++) {
                        System.out.println(i + " - " + todoArray.get(i));
                    }
                    break;
                case "ADD":
                    if (splittedArray[1].matches(patternNumber.pattern())) {
                        int rowNumber = Integer.parseInt(splittedArray[1]);
                        String activity = String.join(" ", Arrays.copyOfRange(splittedArray, 2,
                                splittedArray.length));
                        if (rowNumber > todoArray.size() || rowNumber < 0) {
                            todoArray.add(activity);
                        } else {
                            todoArray.add(rowNumber, activity);
                        }
                    } else {
                        todoArray.add(String.join(" ", Arrays.copyOfRange(splittedArray, 1,
                                splittedArray.length)));
                    }
                    break;
                case "EDIT":
                    if (!splittedArray[1].matches(patternNumber.pattern())) {
                        System.out.println("Wrong command, try again!");
                        continue;
                    } else {
                        int rowNumber = Integer.parseInt(splittedArray[1]);
                        if (rowNumber >= todoArray.size() || rowNumber < 0) {
                            System.out.println("Wrong index, try again!");
                            continue;
                        }
                    }
                    todoArray.set(Integer.parseInt(splittedArray[1]), String.join(" ",
                            Arrays.copyOfRange(splittedArray, 2, splittedArray.length)));
                    break;
                case "DELETE":
                    if (splittedArray.length != 2) {
                        System.out.println("Wrong command, try again!");
                        continue;
                    } else if (!splittedArray[1].matches(patternNumber.pattern())) {
                        System.out.println("Wrong command, try again!");
                        continue;
                    } else {
                        int rowNumber = Integer.parseInt(splittedArray[1]);
                        if (rowNumber >= todoArray.size() || rowNumber < 0) {
                            System.out.println("Wrong index, try again!");
                            continue;
                        }
                    }
                    todoArray.remove(Integer.parseInt(splittedArray[1]));
            }
        }
    }
}

