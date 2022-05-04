package com.github.Diosa34.DMS_HelloWorld.collection;

import com.github.Diosa34.DMS_HelloWorld.parsing.FileVerification;

import java.io.File;
import java.nio.file.Path;
import java.util.LinkedList;

/**
 * Collection of paths to currently executing scripts
 */
public class HistoryOfExecutingScripts extends LinkedList<Path> {
    public static HistoryOfExecutingScripts CollectionOfFiles = new HistoryOfExecutingScripts();

    public static void AddScript(String filepath) {
        if (!FileVerification.isSameLinks(new File(filepath).toPath())) {
            CollectionOfFiles.add(new File(filepath).toPath());
        } else {
            System.out.println(("Файл ${File(it)} уже исполняется в данный момент."));
        }
    }

    public static void RemoveScript(){
        CollectionOfFiles.removeLast();
    }
}