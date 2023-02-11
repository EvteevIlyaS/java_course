import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
import scala.Tuple2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class Main {
//    public static void main(String[] args) {
//        SparkSession spark = SparkSession
//                .builder()
//                .appName("JavaSparkPi")
//                .config("spark.master", "local")
//                .getOrCreate();
//
//        JavaSparkContext jsc = new JavaSparkContext(spark.sparkContext());
//
//        int slices = (args.length == 1) ? Integer.parseInt(args[0]) : 2;
//        int n = 100000 * slices;
//        List<Integer> l = new ArrayList<>(n);
//        for (int i = 0; i < n; i++) {
//            l.add(i);
//        }
//
//        JavaRDD<Integer> dataSet = jsc.parallelize(l, slices);
//
//        int count = dataSet.map(integer -> {
//            double x = Math.random() * 2 - 1;
//            double y = Math.random() * 2 - 1;
//            return (x * x + y * y <= 1) ? 1 : 0;
//        }).reduce((integer, integer2) -> integer + integer2);
//
//        System.out.println("Pi is roughly " + 4.0 * count / n);
//
//        spark.stop();
//    }
    private static final Pattern SPACE = Pattern.compile(" ");

    public static void main(String[] args) throws Exception {

        if (args.length != 2) {
            System.err.println("Usage: JavaWordCount <in_file> <out_file>");
            System.exit(1);
        }

        SparkSession spark = SparkSession
                .builder()
                .appName("JavaWordCount")
                .config("spark.master", "local")
                .config("dfs.client.use.datanode.hostname", "true")
                .getOrCreate();

        JavaRDD<String> lines = spark.read().textFile(args[0]).javaRDD();

        JavaRDD<String> words = lines.flatMap(s -> Arrays.asList(SPACE.split(s)).iterator());

        JavaPairRDD<String, Integer> ones = words.mapToPair(s -> new Tuple2<>(s, 1));

        JavaPairRDD<String, Integer> counts = ones.reduceByKey((i1, i2) -> i1 + i2);

        counts.saveAsTextFile(args[1]);
//        List<Tuple2<String, Integer>> output = counts.collect();
//        for (Tuple2<?,?> tuple : output) {
//            System.out.println(tuple._1() + ": " + tuple._2());
//        }
        spark.stop();
    }
}
