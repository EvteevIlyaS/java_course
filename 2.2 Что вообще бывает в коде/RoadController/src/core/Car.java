package core;

public class Car {
//    String
    public String number;
//    int
    public int height;
//    double
    public double weight;
//    boolean
    public boolean hasVehicle;
    public boolean isSpecial;

    public String toString() {
//        String
        String special = isSpecial ? "СПЕЦТРАНСПОРТ " : "";
        return "\n=========================================\n" +
            special + "Автомобиль с номером " + number +
            ":\n\tВысота: " + height + " мм\n\tМасса: " + weight + " кг";
    }
}
