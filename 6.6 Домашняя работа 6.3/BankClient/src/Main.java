import Clients.Client;
import Clients.Entrepreneur;
import Clients.Individual;
import Clients.LegalEntity;

public class Main {

    public static void main(String[] args) {
        Client le = new LegalEntity(50000);
        Client ind = new Individual(50000);
        Client ent = new Entrepreneur(50000);

        le.showBalance();
        ind.showBalance();
        ent.showBalance();
        System.out.println();

        le.put(20000);
        ind.put(20000);
        ent.put(20000);

        le.showBalance();
        ind.showBalance();
        ent.showBalance();
        System.out.println();

        le.take(10000);
        ind.take(10000);
        ent.take(10000);

        le.showBalance();
        ind.showBalance();
        ent.showBalance();
    }
}
