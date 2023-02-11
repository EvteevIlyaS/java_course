package Clients;

public abstract class Client {
    private double bill;

    public Client(double bill) {
        this.bill = bill;
    }

    double getBill() {
        return bill;
    }

    void setBill(double bill) {
        this.bill = bill;
    }

    public void showBalance() {
        System.out.println("На счете " + bill);
    }

    public abstract void put(double money);
    public abstract void take(double money);
}
