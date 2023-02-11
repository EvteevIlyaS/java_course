package Clients;

public class Entrepreneur extends Client {
    public Entrepreneur(double bill) {
        super(bill);
    }

    @Override
    public void put(double money) {
        if (money < 1000) {
            setBill(getBill() + money * 0.99);
        } else {
            setBill(getBill() + money * 0.995);
        }
    }

    @Override
    public void take(double money) {
        setBill(getBill() - money);
    }
}
