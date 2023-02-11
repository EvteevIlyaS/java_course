import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Main {
    public static void main(String[] args) {
        try {
            Document data = Jsoup.connect("https://lenta.ru").get();
            Elements images = data.select("img");
            images.forEach(img -> {
                String image = img.attr("abs:src");
                if (image.contains(".jpg") | image.contains(".jpeg")) {
                    String[] splitImage = image.split("/");
                    try (InputStream in = new URL(image).openStream()) {
                        Files.copy(in, Paths.get(String.format("images/%s", splitImage[splitImage.length - 1])),
                                StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    System.out.println(splitImage[splitImage.length - 1]);
                }
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
