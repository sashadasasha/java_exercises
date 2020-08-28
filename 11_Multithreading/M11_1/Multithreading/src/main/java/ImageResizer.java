import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.File;
import java.util.List;

public class ImageResizer implements Runnable {

    private final String dstFolder;
    private final int newWidth;
    private final List<File> files;
    private final long start;

    public ImageResizer(String dstFolder, int newWidth, List<File> files, long start) {
        this.dstFolder = dstFolder;
        this.newWidth = newWidth;
        this.files = files;
        this.start = start;
    }

    @Override
    public void run() {
        try {

            for (File file : files) {
                BufferedImage image = ImageIO.read(file);
                if (image == null) {
                    continue;
                }

                int newHeight = (int) Math.round(
                        image.getHeight() / (image.getWidth() / (double) newWidth)
                );

                BufferedImage newImage = Scalr.resize(image, newWidth, newHeight, (BufferedImageOp[])null);

                File newFile = new File(dstFolder + "/" + file.getName());
                System.out.println(newFile.getName());
                ImageIO.write(newImage, FilenameUtils.getExtension(file.getName()), newFile);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        System.out.println("Duration: " + (System.currentTimeMillis() - start));
    }

}
