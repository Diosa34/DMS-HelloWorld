package com.github.Diosa34.DMS_HelloWorld;

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
    public static boolean isSameLinks(Path filePath) throws IOException, SameLinksException {
        for (Path path : HistoryOfExecutingScripts.CollectionOfFiles) {
            if (Files.isSameFile(filePath, path)) {
                throw new SameLinksException();
            }
        }
        return false;
    }

    /**
     * Verification the possibility of creating a file path and permissions
     */
    public static boolean fullVerification(String filename) {
        if (filename == null) {
            throw new NullPointerException("Отсутствует путь к файлу");
        } else if (!FileVerification.existenceCheck(filename)){
            throw new SecurityException("Файл не существует");
        } else if (isDirectoryCheck(filename)){
            throw new SecurityException("Указанный файл является директорией");
        } else if (!FileVerification.readPermissionCheck(new File(filename).toPath()) ||
                    !FileVerification.writePermissionCheck(new File(filename).toPath())
            || !FileVerification.executePermissionCheck(new File(filename).toPath())) {
                throw new SecurityException("Недостаточно прав доступа к файлу");
        }
        return true;
    }
}
