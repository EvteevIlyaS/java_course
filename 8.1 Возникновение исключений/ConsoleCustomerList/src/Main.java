import java.util.Scanner;

public class Main
{
    private static String addCommand = "add Василий Петров " +
            "vasily.petrov@gmail.com +79215637722";
    private static String commandExamples = "\t" + addCommand + "\n" +
            "\tlist\n\tcount\n\tremove Василий Петров";
    private static String commandError = "Wrong command! Available command examples: \n" +
            commandExamples;
    private static String helpText = "Command examples:\n" + commandExamples;

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        CustomerStorage executor = new CustomerStorage();
        for(;;)
        {
            String command = scanner.nextLine();
            String[] tokens = command.split("\\s+", 2);
            try {
                switch (tokens[0]) {
                    case "add" -> executor.addCustomer(tokens[1]);
                    case "list" -> executor.listCustomers();
                    case "remove" -> executor.removeCustomer(tokens[1]);
                    case "count" -> System.out.println("There are " + executor.getCount() + " customers");
                    case "help" -> System.out.println(helpText);
                    default -> System.out.println(commandError);
                }
            } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
