package parsing;

import collection.HistoryOfExecutingScripts;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileVerification {
    private static boolean readPermissionCheck(Path path) {
        return Files.isReadable(path) && Files.isExecutable(path);
    }

    private static boolean writePermissionCheck(Path path) {
        return Files.isReadable(path) && Files.isExecutable(path) && Files.isExecutable(path);
    }

    private static boolean pathCheck(String filename) {
        try {
            Paths.get(filename);
            return true;
        } catch (InvalidPathException ex) {
            System.out.println("Не удаётся составить путь к файлу");
        }
        return false;
    }

    public static boolean isSameLinks(Path filePath) {
        for (Path path : HistoryOfExecutingScripts.CollectionOfFiles) {
            try {
                if (Files.isSameFile(filePath, path)) {
                    return true;
                }
            } catch (IOException e) {
                System.out.println("Файл не найден");
            }
        }
        return false;
    }

    public static boolean fullVerification(String filename) {
        if (FileVerification.pathCheck(filename)) {
            if (!FileVerification.readPermissionCheck(new File(filename).toPath()) || !FileVerification.writePermissionCheck(new File(filename).toPath())) {
                System.out.println("Недостаточно прав доступа к файлу");
                return false;
            }
        } else {
            System.out.println("Файл не найден, введите команду заново");
            return false;
        }
        return true;
    }
}
