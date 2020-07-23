package skill.box94;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static java.nio.file.StandardCopyOption.*;

public class LentaParser {

    public static void main(String[] args) throws IOException {
        /**
         *Адрес папки назначения передан через аргумент
         */
        getImagesFromSite(args[0]);
    }

    private static void getImagesFromSite(String folderAddress) throws IOException {
        Document doc = Jsoup.connect("https://lenta.ru").get();
        Elements images = doc.select("img");
        ArrayList<String> imagesAdresses = new ArrayList<>();
        String pathForImage = folderAddress;
        for (Element image : images) {
            if (image.attr("src").startsWith("https")) {
                imagesAdresses.add(image.attr("src"));
            }
        }

        int index = 1;
        for (String addr : imagesAdresses) {
            URI u = URI.create(addr);
            try (InputStream in = u.toURL().openStream()) {
                if (addr.endsWith(".jpg")) {
                    Files.copy(in, Paths.get(pathForImage + index + ".jpg"), REPLACE_EXISTING);
                } else if (addr.endsWith(".png")) {
                    Files.copy(in, Paths.get(pathForImage + index + ".png"), REPLACE_EXISTING);
                } else {
                    System.out.println("Неверный формат файла");
                }
            }
            index ++;
        }
    }
}
           