import java.util.HashMap;

public class CustomerStorage {
    private HashMap<String, Customer> storage;

    public CustomerStorage() {
        storage = new HashMap<>();
    }

    public void addCustomer(String data) {
        String[] components = data.split("\\s+");
        if (components.length != 4 | !components[2].matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$") |
                !components[3].matches("\\+79[0-9]{9}")) {
            throw new IllegalArgumentException();
        }
        String name = components[0] + " " + components[1];
        storage.put(name, new Customer(name, components[3], components[2]));
    }

    public void listCustomers() {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name) {
        if (name.split("\\s+").length > 1) {
            throw new IllegalArgumentException();
        }
        storage.remove(name);
    }

    public int getCount() {
        return storage.size();
    }
}