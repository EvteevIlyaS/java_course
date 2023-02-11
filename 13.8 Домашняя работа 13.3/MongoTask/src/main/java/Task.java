import com.mongodb.MongoClient;
import com.mongodb.client.ListDatabasesIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.json.JsonMode;
import org.bson.json.JsonWriterSettings;

import javax.print.Doc;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Consumer;

public class Task {
    private static MongoClient mongoClient;
    private static MongoDatabase database;
    private static MongoCollection<Document> collection;
    private static MongoCollection<Document> shops;
    private static MongoCollection<Document> goods;


    public static void main(String[] args) {
//        mongoClient = new MongoClient("127.0.0.1", 27017);
//        database = mongoClient.getDatabase("local");
//        collection = database.getCollection("students");
//        collection.drop();
//
//        try (CSVReader reader = new CSVReaderBuilder(
//                new FileReader("src/main/resources/mongo.csv"))
//                .withSkipLines(1)           // skip the first line, header info
//                .build()) {
//            List<String[]> r = reader.readAll();
//            collection.insertMany(r.stream().map(el -> new Document().append("name", el[0]).append("age",
//                    Integer.parseInt(el[1])).append("courses", el[2])).toList());
//        } catch (IOException | CsvException e) {
//            e.printStackTrace();
//        }
//
////        collection.find().forEach((Consumer<Document>) System.out::println);
//        System.out.printf("Students amount = %d%n", collection.countDocuments());
//
//        BsonDocument query = BsonDocument.parse("{age: {$gt: 40}}");
//        System.out.printf("Students older 40 = %d%n", collection.countDocuments(query));
//
//        BsonDocument query2 = BsonDocument.parse("{age: 1}");
//        collection.find().sort(query2).limit(1).forEach((Consumer<Document>) el -> System.out.println(el.get("name")));
//
//        collection.find().sort(query2).limit(1).forEach((Consumer<Document>) el -> System.out.println(el.get("courses")));
//
//        mongoClient.close();

//======================================================================================================================

        mongoClient = new MongoClient("127.0.0.1", 27017);
        database = mongoClient.getDatabase("local");
        shops = database.getCollection("shops");
        goods = database.getCollection("goods");
        shops.drop();
        goods.drop();

        Scanner scanner = new Scanner(System.in);
        String[] request;
        boolean toContinue = true;

        while (toContinue) {
            request = scanner.nextLine().split("\s");
            switch (request[0]) {
                case "ДОБАВИТЬ_МАГАЗИН" -> addShop(request[1]);
                case "ДОБАВИТЬ_ТОВАР" -> addGood(request[1], Integer.parseInt(request[2]));
                case "ВЫСТАВИТЬ_ТОВАР" -> addGoodToShop(request[1], request[2]);
                case "СТАТИСТИКА_ТОВАРОВ" -> printStatistics();
                default -> {
                    mongoClient.close();
                    toContinue = false;
                }
            }
        }
    }

    private static void addShop(String name) {
        shops.insertOne(new Document().append("shop_name", name).append("goods", new ArrayList<String>()));
    }

    private static void addGood(String name, int amount) {
        goods.insertOne(new Document().append("good_name", name).append("price", amount));
    }

    private static void addGoodToShop(String goodName, String shopName) {
        Bson filter = Filters.eq("shop_name", shopName);
        Bson update = Updates.push("goods", goodName);
        shops.updateMany(filter, update);
    }

    private static void printStatistics() {
        System.out.println("1:");

        BsonDocument query1 = BsonDocument.parse("{_id: \"$shop_name\", goods_count: {$size: \"$goods\"}}");
        shops.aggregate(
                List.of(
                        Aggregates.project(query1)
                )
        ).forEach((Consumer<Document>) doc -> System.out.println(doc.toJson()));

        System.out.println("2-3:");

//        BsonDocument query2 = BsonDocument.parse("{\"good_name\": \"tmp\", \"price\": \"$price\"}");
        goods.aggregate(
                Arrays.asList(
//                        Aggregates.project(query2),
                        Aggregates.group(null, Accumulators.avg("avg_price", "$price"),
                                Accumulators.max("max_price", "$price"),
                                Accumulators.min("min_price", "$price"))
                )
        ).forEach((Consumer<Document>) doc -> System.out.println(doc.toJson()));

        System.out.println("4:");

        System.out.println(goods.aggregate(
                Arrays.asList(
                        Aggregates.match(Filters.lt("price", 100)),
                        Aggregates.count()
                )
        ).first().get("count"));
    }
}