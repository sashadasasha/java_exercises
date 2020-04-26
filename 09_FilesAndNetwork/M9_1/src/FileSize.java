import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileSize {
    private static final Path FILE_PATH = Paths.get("/media/sasha/3c0c1705-6766-4724-8a72-b46458c5d3401/Документы/");
    private static long size = 0; //bytes
    private static boolean isItDirectory = false;
    public static void main(String[] args) throws IOException {
        getFileSize(FILE_PATH);
        getValuesOfSize(size);
    }

    public static void getFileSize(Path path) throws IOException {
        if (Files.isDirectory(path)) {
            isItDirectory = true;
        Stream<Path> files = Files.list(path);
        files.forEach(file -> {
            if (Files.isDirectory(file)) {
                try {
                    getFileSize(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    size += Files.size(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        } else {
            size += Files.size(path);
        }
    }

    public static void getValuesOfSize(long size) {
        String typeOfFile = "";
        if (isItDirectory) {
            typeOfFile = "Директории";
        } else {
            typeOfFile = "Файла";
        }
        System.out.printf("Размер %s:%n" +
                "%s байт%n" +
                "%s Кбайт%n" +
                "%s Мбайт%n" +
                "%s Гбайт%n", typeOfFile, size, (double) size/1024, (double) size/(1024*1024), (double) size/(1024*1024*1024));
    }
}

//    Размер Директории:
//        19961409360 байт
//        1.9493563828125E7 Кбайт
//        19036.68342590332 Мбайт
//        18.59051115810871 Гбайт