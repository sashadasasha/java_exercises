<<<<<<< HEAD
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Main
{
    public static void main(String[] args)
    {
        String srcFolder = "/users/sortedmap/Desktop/src";
        String dstFolder = "/users/sortedmap/Desktop/dst";

        File srcDir = new File(srcFolder);

        long start = System.currentTimeMillis();

        File[] files = srcDir.listFiles();

        try
        {
            for(File file : files)
            {
                BufferedImage image = ImageIO.read(file);
                if(image == null) {
                    continue;
                }

                int newWidth = 300;
                int newHeight = (int) Math.round(
                        image.getHeight() / (image.getWidth() / (double) newWidth)
                );
                BufferedImage newImage = new BufferedImage(
                        newWidth, newHeight, BufferedImage.TYPE_INT_RGB
                );

                int widthStep = image.getWidth() / newWidth;
                int heightStep = image.getHeight() / newHeight;

                for (int x = 0; x < newWidth; x++)
                {
                    for (int y = 0; y < newHeight; y++) {
                        int rgb = image.getRGB(x * widthStep, y * heightStep);
                        newImage.setRGB(x, y, rgb);
                    }
                }

                File newFile = new File(dstFolder + "/" + file.getName());
                ImageIO.write(newImage, "jpg", newFile);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

        System.out.println("Duration: " + (System.currentTimeMillis() - start));
    }
}
=======
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
>>>>>>> module11_1
