import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.fs.permission.FsPermission;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileAccess
{
    private String rootPath;
    Configuration configuration = new Configuration();
    FileSystem hdfs;
    /**
     * Initializes the class, using rootPath as "/" directory
     *
     * @param rootPath - the path to the root of HDFS,
     * for example, hdfs://localhost:32771
     */
    public FileAccess(String rootPath) throws URISyntaxException, IOException {
        this.rootPath = rootPath;
        configuration.set("dfs.client.use.datanode.hostname", "true");
        configuration.set("dfs.client.block.write.replace-datanode-on-failure.policy", "NEVER");
        configuration.set("dfs.client.block.write.replace-data node-on-failure.enable", "true");
        configuration.setBoolean("dfs.support.append", true);

        System.setProperty("HADOOP_USER_NAME", "root");

//        hdfs = FileSystem.get(
//                new URI("hdfs://8d1d005e9b74:8020"), configuration
//        );
    }

    /**
     * Creates empty file or directory
     *
     * @param path
     */
    public void create(String path) throws IOException, URISyntaxException {
        hdfs = FileSystem.get(
                new URI(rootPath), configuration
        );
        Path pathToCreate = new Path(path);

        if (hdfs.exists(pathToCreate)) {
            hdfs.delete(pathToCreate, true);
        }

        FileSystem.create(hdfs, pathToCreate, new FsPermission("777"));
        hdfs.close();
    }

    /**
     * Appends content to the file
     *
     * @param path
     * @param content
     */
    public void append(String path, String content) throws IOException, URISyntaxException {
        hdfs = FileSystem.get(
                new URI(rootPath), configuration
        );
//        Boolean isAppendable = Boolean.valueOf(hdfs.getConf().get("dfs.support.append"));
//        Path pathToAppend = new Path(path);
//
//        if(isAppendable) {
//            FSDataOutputStream fs_append = hdfs.append(pathToAppend);
//            PrintWriter writer = new PrintWriter(fs_append);
//            writer.append(content);
//            writer.flush();
//            fs_append.hflush();
//            writer.close();
//            fs_append.close();
//        }
        Path pathToCreate = new Path(path);

        OutputStream os = hdfs.append(pathToCreate);
        BufferedWriter br = new BufferedWriter(
                new OutputStreamWriter(os, "UTF-8")
        );

        br.write(content);

        br.flush();
        br.close();
        hdfs.close();

    }

    /**
     * Returns content of the file
     *
     * @param path
     * @return
     */
    public String read(String path) throws URISyntaxException, IOException {
        hdfs = FileSystem.get(
                new URI(rootPath), configuration
        );

        Path pathToAppend = new Path(path);
        InputStream is = hdfs.open(pathToAppend);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String res = br.lines().collect(Collectors.joining("\n"));

        hdfs.close();
        return res;
    }

    /**
     * Deletes file or directory
     *
     * @param path
     */
    public void delete(String path) throws IOException, URISyntaxException {
        hdfs = FileSystem.get(
                new URI(rootPath), configuration
        );

        Path pathToDelete = new Path(path);
        if (hdfs.exists(pathToDelete)) {
            hdfs.delete(pathToDelete, true);
        }

        hdfs.close();

    }

    /**
     * Checks, is the "path" is directory or file
     *
     * @param path
     * @return
     */
    public boolean isDirectory(String path) throws URISyntaxException, IOException {
        hdfs = FileSystem.get(
                new URI(rootPath), configuration
        );

        Path pathToSth = new Path(path);
        boolean result = hdfs.isDirectory(pathToSth);

        hdfs.close();
        return result;
    }

    /**
     * Return the list of files and subdirectories on any directory
     *
     * @param path
     * @return
     */
    public List<String> list(String path) throws URISyntaxException, IOException {
        hdfs = FileSystem.get(
                new URI(rootPath), configuration
        );

        List<String> result = new ArrayList<>();

        RemoteIterator<LocatedFileStatus> fileStatusListIterator = hdfs.listFiles(
                new Path(path), true);
        while(fileStatusListIterator.hasNext()){
            LocatedFileStatus fileStatus = fileStatusListIterator.next();
            result.add(fileStatus.getPath().toString());
        }

//        FileStatus[] statuses = hdfs.globStatus(new Path(path));
//        for(FileStatus status: statuses) {
//            result.add(status.getPath().toString());
//        }
        return result;
    }
}
