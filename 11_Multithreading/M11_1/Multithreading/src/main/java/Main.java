import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
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
        List<Thread> threadList = new ArrayList<>();
        for (List<File> f : listOfLists) {
            ImageResizer imageResizer = new ImageResizer(dstFolder, 300, f, start);
            Thread th = new Thread(imageResizer);
            threadList.add(th);
        }

        threadList.forEach(Thread::start);
        threadList.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}