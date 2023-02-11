import java.io.File;

public class Main {
    public static void main(String[] args) {
        String srcFolder = "data/src";
        String dstFolder = "data/dst";

        File srcDir = new File(srcFolder);

        File[] files = srcDir.listFiles();

        long start = System.currentTimeMillis();

        int cores = Runtime.getRuntime().availableProcessors();
        assert files != null;
        int imagesOnThread = files.length / cores;

        File[] newFiles;
        for (int i = 0; i < 4; i++) {
            newFiles = (i == cores - 1) ? new File[files.length - imagesOnThread * i] : new File[imagesOnThread];
            System.arraycopy(files, imagesOnThread * i, newFiles, 0, newFiles.length);
            new Resizer(newFiles, dstFolder, start).start();
        }
    }
}
