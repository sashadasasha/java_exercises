import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

class FilesCopy {
    private static boolean isItDirectory = false;

    public static void main(String[] args) throws IOException {
        getCopyFile(Paths.get(args[0]), Paths.get(args[1]));
        System.out.println("Папка скопирована!");
    }

    public static void getCopyFile(Path oldRoute, Path newRoute) throws IOException {
        Files.walkFileTree(oldRoute, new FileCopyVisitor(oldRoute, newRoute));
    }

    public static class FileCopyVisitor extends SimpleFileVisitor<Path> {
        private Path source, destination;

        public FileCopyVisitor(Path sourcePath, Path desPath) {
            this.source = sourcePath;
            this.destination = desPath;
        }

        public FileVisitResult visitFile(Path path, BasicFileAttributes fileAttributes) {
            Path newDes = destination.resolve(source.relativize(path));
            try {
                Files.copy(path, newDes, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            return FileVisitResult.CONTINUE;
        }

        public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes fileAttributes) {
            Path newDes = destination.resolve(source.relativize(path));
            try {
                if (Files.exists(newDes)) {
                    FileUtils.deleteDirectory(new File(String.valueOf(newDes)));
                }
                Files.copy(path, newDes, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            return FileVisitResult.CONTINUE;
        }
    }
}
