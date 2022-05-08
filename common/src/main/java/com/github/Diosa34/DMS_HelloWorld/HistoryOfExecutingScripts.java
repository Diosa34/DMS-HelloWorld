package com.github.Diosa34.DMS_HelloWorld;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.LinkedList;

/**
 * Collection of paths to currently executing scripts
 */
public class HistoryOfExecutingScripts extends LinkedList<Path> {
    public static HistoryOfExecutingScripts CollectionOfFiles = new HistoryOfExecutingScripts();

    public static void addScript(Logger logger, String filepath) {
        try {
            if (!FileVerification.isSameLinks(new File(filepath).toPath())) {
                CollectionOfFiles.add(new File(filepath).toPath());
            }
        } catch(SameLinksException | IOException e){
            logger.print(SameLinksException.message);
        }
    }

    public static void removeScript(){
        CollectionOfFiles.removeLast();
    }
}