import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Resizer extends Thread {
    private final File[] files;
    private final String dstFolder;
    private final long start;

    public Resizer(File[] files, String dstFolder, long start) {
        this.files = files;
        this.dstFolder = dstFolder;
        this.start = start;
    }

    @Override
    public void run() {
        for (File file : files) {
            resize(file, dstFolder);
        }
        System.out.println("Time consumed: " + (System.currentTimeMillis() - start));
    }

    private void resize(File file, String dstFolder) {
        BufferedImage image;
        try {
            image = ImageIO.read(file);
            image = Scalr.resize(image,
                    Scalr.Method.SPEED,
                    Scalr.Mode.AUTOMATIC,
                    200,
                    (int) Math.round(
                            image.getHeight() / (image.getWidth() / (double) 300)));
            File newFile = new File(dstFolder + "/" + file.getName());
            ImageIO.write(image, "jpg", newFile);
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}
