import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) throws IOException {
//        Document doc = Jsoup.connect("https://skillbox.ru").get();
//        Elements urls = doc.select("a[href^=https://skillbox]");
//        System.out.println(urls.size());
//        urls.stream().map(el -> el.attr("href")).forEach(System.out::println);

        Url rootUrl = new Url("https://skillbox.ru");

        new ForkJoinPool().invoke(new RecursiveWebParser(rootUrl));
    }
}
