public class Main {
    public static void main(String[] args) {
//        Container container = new Container();
//        container.count += 7843;
//        System.out.println(sumDigits(5115));
//        System.out.println(Byte.MAX_VALUE + " " + Byte.MIN_VALUE);
//        System.out.println(Integer.MAX_VALUE + " " + Integer.MIN_VALUE);
//        System.out.println(Short.MAX_VALUE + " " + Short.MIN_VALUE);
//        System.out.println(Long.MAX_VALUE + " " + Long.MIN_VALUE);
//        System.out.println(Double.MAX_VALUE + " " + Double.MIN_VALUE);
//        System.out.println(Float.MAX_VALUE + " " + Float.MIN_VALUE);
    }

    public static Integer sumDigits(Integer number) {
        int res = 0;
        for (int i = 0; i < number.toString().length(); i++) {
//            res += Integer.parseInt(String.valueOf(number.toString().charAt(i)));
            res += Character.getNumericValue(number.toString().charAt(i));
        }

        return res;
    }
}
