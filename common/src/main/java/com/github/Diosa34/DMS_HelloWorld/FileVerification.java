package com.github.Diosa34.DMS_HelloWorld;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Class for file link verifications
 */
public class FileVerification {

    private FileVerification(){}

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
     * Verification the possibility of creating a file path and permissions
     */
    public static boolean fullVerification(String filename) throws FileNotFoundException, FileVerificationException {
        if (filename == null) {
            throw new FileNotFoundException("Отсутствует путь к файлу");
        } else if (!FileVerification.existenceCheck(filename)){
            throw new FileVerificationException("Файл не существует");
        } else if (isDirectoryCheck(filename)){
            throw new FileVerificationException("Указанный файл является директорией");
        } else if (!FileVerification.executePermissionCheck(new File(filename).toPath())) {
                throw new FileVerificationException("Недостаточно прав доступа к файлу");
        }
        return true;
    }
}
