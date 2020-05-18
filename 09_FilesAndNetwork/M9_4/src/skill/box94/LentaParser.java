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
            imagesAdresses.add(image.attr("src"));
        }

        /**
         * Два последних адреса вида //mc.yandex.ru/watch/27714477
         * //counter.rambler.ru/top100.cnt?pid=80674
         *
         * Удалила их из списка просто вручную.
         */
        imagesAdresses.remove(imagesAdresses.size()-1);
        imagesAdresses.remove(imagesAdresses.size()-1);

        int index = 1;
        for (String addr : imagesAdresses) {
            System.out.println(addr);
            URI u = URI.create(addr);
            try (InputStream in = u.toURL().openStream()) {
                Files.copy(in, Paths.get(pathForImage + index + ".jpg"), REPLACE_EXISTING);
            }
            index ++;
        }
    }
}
           