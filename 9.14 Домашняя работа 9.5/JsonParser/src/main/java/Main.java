import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    private static List<HashMap<String, String>> lines;
    private static HashMap<String, List<String>> stations;
    private static List<String> tmpList;
    private static HashMap<String, String> tmpHashMap;
    private static String lineNumber;

    public static void main(String[] args) {
        try {
            Document data = Jsoup.connect("https://www.moscowmap.ru/metro.html#lines").maxBodySize(0).get();
            writeJsonStations(data);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void writeJsonStations(Document data) {
        lines = new ArrayList<>();
        stations = new HashMap<>();

//        lines
        Elements elements = data.select("span.js-metro-line");
        elements.forEach(ln -> {
            tmpHashMap = new HashMap<>();
            tmpHashMap.put("number", ln.attr("data-line").trim());
            tmpHashMap.put("name", ln.text().trim());
            lines.add(tmpHashMap);
        });
//        stations
        elements = data.select("div.js-metro-stations");
        elements.forEach(el -> {
            lineNumber = el.attr("data-line").trim();
            tmpList = new ArrayList<>();
            stations.put(lineNumber, tmpList);
            el.select("span.name").forEach(st -> tmpList.add(st.text().trim()));
        });

//        json write
        JSONObject metroJson = new JSONObject();
        metroJson.put("stations", stations);
        metroJson.put("lines", lines);

        try (FileWriter file = new FileWriter("data/metro.json")) {
            file.write(metroJson.toJSONString());
            file.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }


    }
}
