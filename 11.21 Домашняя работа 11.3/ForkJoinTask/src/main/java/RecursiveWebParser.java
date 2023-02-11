import netscape.javascript.JSObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Collector;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

public class RecursiveWebParser extends RecursiveTask<StringBuffer> {
    private Url url;

    public RecursiveWebParser(Url url) {
        this.url = url;
    }

    @Override
    protected StringBuffer compute() {
        StringBuffer resUrlTree = new StringBuffer().append(url.getUrl()).append("\n");

        List<RecursiveWebParser> taskList = new ArrayList<>();
        for (String subUrl :
                url.getSubUrls()) {
            RecursiveWebParser task = new RecursiveWebParser(new Url(subUrl));
            task.fork();
            taskList.add(task);
        }

        for (RecursiveWebParser task:
             taskList) {
            resUrlTree.append(task.join());
        }
        System.out.println(resUrlTree.toString());
        return resUrlTree;
    }
}
