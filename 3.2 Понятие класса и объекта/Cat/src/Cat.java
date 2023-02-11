
public class Cat {
    private static int count = 0;

    private double originWeight;
    private double weight;

    private static final int eyesAmount = 2;
    private static final double minWeight = 1000.0;
    private static final double maxWeight = 9000.0;

    private double eatenFoodAmount = 0.0;
    private CatColor color;

    public Cat() {
        count++;
        eatenFoodAmount = 0.0;
        weight = 1500.0 + 3000.0 * Math.random();
        originWeight = weight;
        color = CatColor.GRAY;
    }

    public Cat(double weight) {
        this();
        this.weight = weight;
    }

    public Cat deepCopy () {
        Cat cat_copy = new Cat();
        cat_copy.setColor(this.getColor());
        cat_copy.setWeight(this.getWeight());
        return cat_copy;
    }

    public void setColor(CatColor color) {
        this.color = color;
    }

    public CatColor getColor() {
        return this.color;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }


    public void meow() {
        weight = weight - 1;
        System.out.println("Meow");
    }

    public static void getCount() {
        System.out.println("Кол-во кошек = " + count);
    }

    public void pee() {
        weight--;
        System.out.println("Кошка пописала");
    }

    public void feed(Double amount) {
        weight = weight + amount;
        eatenFoodAmount += amount;
    }

    public void getEatenFoodAmount() {
        System.out.println("Кошка съела " + (int) eatenFoodAmount + " кол-во еды");
    }

    public void drink(Double amount) {
        weight = weight + amount;
    }

    public Double getWeight() {
        return weight;
    }

    public String getStatus() {
        if (weight < minWeight) {
            return "Dead";
        } else if (weight > maxWeight) {
            count--;
            return "Exploded";
        } else if (weight > originWeight) {
            return "Sleeping";
        } else {
            return "Playing";
        }
    }
}