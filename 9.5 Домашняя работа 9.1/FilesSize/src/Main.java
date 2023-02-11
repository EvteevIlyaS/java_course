import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        try {
//            getDirectorySizeJava8(Paths.get("/Users/EvteevIS/JavaProjects/Java - курс Skillbox"));
            copyDirectory("/Users/EvteevIS/JavaProjects/Java - курс Skillbox/9.5 Домашняя работа 9.1",
                    "/Users/EvteevIS/JavaProjects/Java - курс Skillbox/8.5 Отладка приложений");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void copyDirectory(String sourceDirectoryLocation, String destinationDirectoryLocation)
            throws IOException {
        String[] splitSourceDirectoryLocation = sourceDirectoryLocation.split("/");
        Files.walk(Paths.get(sourceDirectoryLocation))
                .forEach(source -> {
                    Path destination = Paths.get(destinationDirectoryLocation,
                            splitSourceDirectoryLocation[splitSourceDirectoryLocation.length - 1],
                            source.toString().substring(sourceDirectoryLocation.length()));
                    System.out.printf("%s -> %s\n", source, destination);
                    try {
                        Files.copy(source, destination);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }

    // size of directory in bytes
    public static void getDirectorySizeJava8(Path path) throws IOException {
        double to_mb = 1048576.;
        long size = 0;

        // need close Files.walk
        Stream<Path> walk = Files.walk(path);

        size = walk
                .filter(Files::isRegularFile)
                .mapToLong(p -> {
                    try {
                        System.out.println(p.getFileName() +
                                String.format(" - %.4fMb", Files.size(p) / to_mb));
                        return Files.size(p);
                    } catch (IOException e) {
                        System.out.println("Failed to get size of a file " + p.getFileName());
                        return 0L;
                    }
                })
                .sum();


        System.out.println("\nРазмер папки " + path + " составляет " + String.format("%.4fMb", size / to_mb));

    }
}
