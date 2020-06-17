import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.util.List;

public class Main
{
    private static String symbols = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static void main(String[] args) throws Exception
    {
        String root = "hdfs://df1d653b7244:8020";

        FileAccess fileAccess = new FileAccess(root);

        String pathOne = root + "/test/fileTest_1.txt";
        String pathTwo = root + "/test/fileTest_2.txt";
        String pathThree = root + "/test/files/file/";

        fileAccess.create(pathOne);
        fileAccess.create(pathTwo);
        fileAccess.create(pathThree);

        System.out.println("- isDirectory - " + fileAccess.isDirectory(pathOne));
        System.out.println("- isDirectory - " + fileAccess.isDirectory(pathTwo));
        System.out.println("- isDirectory - " + fileAccess.isDirectory(root+"/test/files/"));

        fileAccess.append(pathOne, "Yep! I did it!");
        fileAccess.append(pathTwo, "Hello world!");

        System.out.println("- READ first file - " + fileAccess.read(pathOne));
        System.out.println("- READ second file - " + fileAccess.read(pathTwo));

        fileAccess.append(pathTwo, " Java course finished!");

        System.out.println("- READ second file - " + fileAccess.read(pathTwo));

        System.out.println("- DELETE first file");
        fileAccess.delete(pathOne);


        System.out.println("- PRINT LIST files from directory");
        List<String> listFiles = fileAccess.list(root + "/");
        for (String file: listFiles){
            System.out.println(file);
        }

        fileAccess.close();
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
