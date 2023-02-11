import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class Main {
    private static long finalIncome;
    private static long finalExpense;

    public static void main(String[] args) {
        try {
            List<String> allLines = Files.readAllLines(Paths.get("data/movementList.csv"));
            allLines = allLines.subList(1, allLines.size());
            HashMap<String, HashMap<String, Long>> bankData = new HashMap<>();

            allLines.stream().map(str -> str.split(",")).filter(el -> el.length == 8).forEach(el -> {
                String description = el[5];
                long income = Integer.parseInt(el[6]);
                long expense = Integer.parseInt(el[7]);

                if (!bankData.containsKey(description)) {
                    bankData.put(description, new HashMap<String, Long>());
                    bankData.get(description).put("income", income);
                    bankData.get(description).put("expense", expense);
                } else {
                    bankData.get(description).put("income", bankData.get(description).get("income") + income);
                    bankData.get(description).put("expense", bankData.get(description).get("expense") + expense);
                }

                finalIncome += income;
                finalExpense += expense;
            });

            System.out.printf("Final income = %s\nFinal expense = %s\n", finalIncome, finalExpense);
            bankData.forEach((desc, res) -> System.out.printf("%s:\nincome = %s\nexpense = %s\n",
                    desc, res.get("income"), res.get("expense")));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
