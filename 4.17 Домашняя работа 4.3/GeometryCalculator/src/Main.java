public class Main {
    public static void main(String[] args) {
        task(55);
//        System.out.println("Circle Square");
//        System.out.println(GeometryCalculator.getCircleSquare(3d));
//        System.out.println(GeometryCalculator.getCircleSquare(-5));
//        System.out.println(GeometryCalculator.getCircleSquare(6.56));
//
//        System.out.println("\nSphere Volume");
//        System.out.println(GeometryCalculator.getSphereVolume(1d));
//        System.out.println(GeometryCalculator.getSphereVolume(57.6));
//        System.out.println(GeometryCalculator.getSphereVolume(0));
//
//        System.out.println("\nis Triangle Right");
//        System.out.println(GeometryCalculator.isTriangleRightAngled(0, 0, 0));
//        System.out.println(GeometryCalculator.isTriangleRightAngled(1, 2, 3));
//        System.out.println(GeometryCalculator.isTriangleRightAngled(5, 5, 11));
//
//        System.out.println("\nTriangle Square");
//        System.out.println(GeometryCalculator.getTriangleSquare(4.0, 10.0, 5.0));
//        System.out.println(GeometryCalculator.getTriangleSquare(7.0, 7.0, 8.10));
//        System.out.println(GeometryCalculator.getTriangleSquare(4.0, 7.0, 8.10));
    }

    public static void task(int boxCount) {
        final int maxContainers = 12;
        final int maxBoxes = 27;

        int containersAmount = (int) Math.ceil((double) boxCount / maxBoxes);
        int cargoAmount = (int) Math.ceil((double) containersAmount / maxContainers);

        for (int i = 1; i <= cargoAmount; i++) {
            System.out.println("Грузовик " + i + ":");
            for (int j = 1; j <= maxContainers; j++) {
                System.out.println("\tКонтейнер " + j + ":");
                for (int k = 1; k <= 27; k++) {
                    System.out.println("\t\tЯщик " + k);
                    if (--boxCount == 0){
                        return;
                    }
                }
            }
        }

//        System.out.println(containersAmount);
//        System.out.println(cargoAmount);

//        int cargoAmount = 1;
//        int containersAmount = 1;
//
//        while (true) {
//            if (boxCount > 0) {
//                System.out.println("Грузовик " + cargoAmount + ":");
//                while (true) {
//
//                }
//            }
//        }
    }
}
