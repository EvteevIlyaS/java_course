import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URI;

public class Main
{
    private static String symbols = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static void main(String[] args) throws Exception
    {
//        Configuration configuration = new Configuration();
//        configuration.set("dfs.client.use.datanode.hostname", "true");
//        System.setProperty("HADOOP_USER_NAME", "root");
//
//        FileSystem hdfs = FileSystem.get(
//            new URI("hdfs://8d1d005e9b74:8020"), configuration
//        );
//        Path file = new Path("hdfs://8d1d005e9b74:8020/test/file.txt");
//
//        if (hdfs.exists(file)) {
//            hdfs.delete(file, true);
//        }
//
//        OutputStream os = hdfs.create(file);
//        BufferedWriter br = new BufferedWriter(
//            new OutputStreamWriter(os, "UTF-8")
//        );
//
//        for(int i = 0; i < 10_000_000; i++) {
//            br.write(getRandomWord() + " ");
//        }
//
//        br.flush();
//        br.close();
//        hdfs.close();

        FileAccess fileAccess = new FileAccess("hdfs://55b4bf3b89de:8020/");
        fileAccess.create("hdfs://55b4bf3b89de/file1.txt");
        fileAccess.append("hdfs://55b4bf3b89de:8020/file1.txt", "\nsasdasdth_cooasdaaaal\nasd\nasd");
//        System.out.println(fileAccess.read("hdfs://8d1d005e9b74:8020/test2/file1.txt"));
//        fileAccess.delete("hdfs://8d1d005e9b74:8020/test2/file1.txt");
//        System.out.println(fileAccess.isDirectory("hdfs://8d1d005e9b74:8020/test"));
//        fileAccess.list("hdfs://8d1d005e9b74:8020/").forEach(System.out::println);
    }

    private static String getRandomWord()
    {
        StringBuilder builder = new StringBuilder();
        int length = 2 + (int) Math.round(10 * Math.random());
        int symbolsCount = symbols.length();
        for(int i = 0; i < length; i++) {
            builder.append(symbols.charAt((int) (symbolsCount * Math.random())));
        }
        return builder.toString();
    }
}
