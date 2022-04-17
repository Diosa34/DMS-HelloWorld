package com.github.Diosa34.DMS_HelloWorld.parsing;

import com.github.Diosa34.DMS_HelloWorld.collection.HistoryOfExecutingScripts;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Class for file link verifications
 */
public class FileVerification {

    private FileVerification(){}
    /**
     * Read access verification
     */
    private static boolean readPermissionCheck(Path path) {
        return Files.isReadable(path);
    }

    /**
     * Write access verification
     */
    private static boolean writePermissionCheck(Path path) {
        return Files.isWritable(path);
    }

    /**
     * Execute access verification
     */
    private static boolean executePermissionCheck(Path path) {
        return Files.isExecutable(path);
    }

    /**
     * Verification the possibility of creating a file path
     */
    private static boolean pathCheck(String filename) {
        try {
            Paths.get(filename);
            return true;
        } catch (InvalidPathException ex) {
            System.out.println("Не удаётся составить путь к файлу");
        }
        return false;
    }

    /**
     * Existence verification
     */
    private static boolean existenceCheck(String filename) {
        return Files.exists(new File(filename).toPath());
    }

    /**
     * Is it directory verification
     */
    private static boolean isDirectoryCheck(String filename){return Files.isDirectory(new File(filename).toPath());}

    /**
     * Link Identity verification
     */
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

    /**
     * Verification the possibility of creating a file path and permissions
     */
    public static boolean fullVerification(String filename) {
        if (filename == null) {
            System.out.println("Файл не найден");
            return false;
        } else if (!FileVerification.existenceCheck(filename)){
            System.out.println("Указанного файла не существует");
            return false;
        } else if (isDirectoryCheck(filename)){
            System.out.println("Указанный файл является директорией");
            return false;
        } else if (FileVerification.pathCheck(filename)) {
            if (!FileVerification.readPermissionCheck(new File(filename).toPath()) ||
                    !FileVerification.writePermissionCheck(new File(filename).toPath())
            || !FileVerification.executePermissionCheck(new File(filename).toPath())) {
                System.out.println("Недостаточно прав доступа к файлу");
                return false;
            }
        }
        return true;
    }
}
