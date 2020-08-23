import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String srcFolder = "C:\\Users\\Sasha\\Desktop\\src";
        String dstFolder = "C:\\Users\\Sasha\\Desktop\\dst";

        File srcDir = new File(srcFolder);

        int processes = Runtime.getRuntime().availableProcessors();
        List<File> files = Arrays.asList(srcDir.listFiles());
        List<List<File>> listOfLists = new ArrayList<>();

        for(int i = 0; i < processes; i ++) {
            List<File> list = new ArrayList<>();
            listOfLists.add(list);
        }

        for (int i = 0; i < files.size(); i ++) {
            if (i < processes) {
                listOfLists.get(i).add(files.get(i));
                continue;
            } else {
                listOfLists.get(i % processes).add(files.get(i));
                continue;
            }
        }

        long start = System.currentTimeMillis();
        for (List<File> f : listOfLists) {
            ImageResizer imageResizer = new ImageResizer(dstFolder, 300, f, start);
            new Thread(imageResizer).start();
        }
    }
}