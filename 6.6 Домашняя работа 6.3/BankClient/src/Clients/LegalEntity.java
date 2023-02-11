package Clients;

public class LegalEntity extends Client{
    public LegalEntity(double bill) {
        super(bill);
    }

    @Override
    public void put(double money) {
        setBill(getBill() + money);
    }

    @Override
    public void take(double money) {
        setBill(getBill() - money * 1.01);
    }
}
