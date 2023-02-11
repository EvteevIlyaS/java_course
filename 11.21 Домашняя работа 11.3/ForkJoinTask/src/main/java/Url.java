import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Url {
    private final String url;
    private Set<String> subUrls;

    public Url(String url) {
        this.url = url;
        try {
            Document doc = Jsoup.connect(url).get();
            Elements urls = doc.select("a[href^=https://skillbox]");
            this.subUrls = urls.stream().map(el -> el.attr("href")).collect(Collectors.toSet());
        } catch (IOException ex) {
            System.out.println("Ничего не найдено");
            this.subUrls = new HashSet<>();
        }
    }

    public String getUrl() {
        return url;
    }

    public Set<String> getSubUrls() {
        return subUrls;
    }
}
