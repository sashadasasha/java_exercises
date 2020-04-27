import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

class FilesCopy {
    private static boolean isItDirectory = false;

    public static void main(String[] args) throws IOException {
        getCopyFile(Paths.get(args[0]), Paths.get(args[1]));
        System.out.println("Папка скопирована!");
    }

    public static void getCopyFile(Path oldRoute, Path newRoute) throws IOException {
        Files.list(oldRoute).forEach(file -> {
            if (Files.isDirectory(file)) {
                try {
                    Path nameFolder = file.getFileName();
                    Path destinationRoute = Paths.get(newRoute + "/" + nameFolder);
                    Path originalRoute = Paths.get(oldRoute + "/" + nameFolder);
                    if (Files.exists(destinationRoute)) {
                        FileUtils.deleteDirectory(new File(String.valueOf(destinationRoute)));
                    }
                    Files.copy(originalRoute, destinationRoute);
                    getCopyFile(originalRoute, destinationRoute);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    Files.copy(file, Paths.get(newRoute + "/" + file.getFileName()), StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
