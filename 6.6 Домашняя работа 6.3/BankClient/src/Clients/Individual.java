package Clients;

public class Individual extends Client{
    public Individual(double bill) {
        super(bill);
    }

    @Override
    public void put(double money) {
        setBill(getBill() + money);
    }

    @Override
    public void take(double money) {
        setBill(getBill() - money);
    }
}
