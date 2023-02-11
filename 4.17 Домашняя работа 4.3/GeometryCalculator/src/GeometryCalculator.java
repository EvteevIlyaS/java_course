import static java.lang.Math.*;
import static java.lang.Math.sqrt;

public class GeometryCalculator {
    // метод должен использовать абсолютное значение radius
    public static double getCircleSquare(double radius) {
        return PI * pow(radius, 2);
    }

    // метод должен использовать абсолютное значение radius
    public static double getSphereVolume(double radius) {
        return 4. / 3 * PI * pow(radius, 3);
    }

    public static boolean isTriangleRightAngled(double a, double b, double c) {
        return (pow(a, 2) == pow(b, 2) + pow(c, 2) || pow(c, 2) == pow(b, 2) + pow(a, 2) ||
                pow(b, 2) == pow(c, 2) + pow(a, 2)) && a != 0 && b != 0 && c != 0;
    }

    // перед расчетом площади рекомендуется проверить возможен ли такой треугольник
    // методом isTriangleRightAngled, если невозможен вернуть -1.0
    public static double getTriangleSquare(double a, double b, double c) {
        double semiPerimeter = (a + b + c) / 2;
        double pre_res = semiPerimeter * (semiPerimeter - a) * (semiPerimeter - b) * (semiPerimeter - c);
        return pre_res > 0 ? sqrt(pre_res) : -1;
    }
}
