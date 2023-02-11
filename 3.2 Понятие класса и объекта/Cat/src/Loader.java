import com.skillbox.airport.Airport;

public class Loader {
    public static void main(String[] args) {
        Airport airport = Airport.getInstance();
        System.out.println(airport.getAllAircrafts());
//        Cat cat = new Cat();
//        cat.setWeight(123);
//        cat.setColor(CatColor.WHITE);
//        System.out.println(cat.getWeight() + " " + cat.getColor());
//        Cat copiedCat = cat.deepCopy();
//        System.out.println(copiedCat.getWeight() + " " + copiedCat.getColor());
//        copiedCat.setColor(CatColor.BLACK);
//        System.out.println(copiedCat.getWeight() + " " + copiedCat.getColor());
//        System.out.println(cat.getColor());
//        cat.setColor(CatColor.BLACK);
//        System.out.println(cat.getColor());
//        catsGenerator();
/*
        Cat cat1 = new Cat();
        Cat.getCount();
        Cat cat = new Cat();
        Cat.getCount();
        while (true) {
            cat.feed(2000.0);
            System.out.println(cat.getWeight());
            System.out.println(cat.getStatus());
            Cat.getCount();
            if (cat.getStatus().equals("Exploded")) {
                break;
            }
        }
*/
    }

    public static void catsGenerator() {
        Cat cat1 = new Cat(100);
        System.out.println(cat1.getWeight());
        Cat cat2 = new Cat(250);
        System.out.println(cat2.getWeight());
        Cat cat3 = new Cat(370);
        System.out.println(cat3.getWeight());
    }
}