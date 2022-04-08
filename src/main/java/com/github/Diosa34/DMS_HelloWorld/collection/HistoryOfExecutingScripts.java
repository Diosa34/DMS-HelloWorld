package com.github.Diosa34.DMS_HelloWorld.collection;

import java.nio.file.Path;
import java.util.LinkedList;

/**
 * Collection of paths to currently executing scripts
 */
public class HistoryOfExecutingScripts extends LinkedList<Path> {
    public static HistoryOfExecutingScripts CollectionOfFiles = new HistoryOfExecutingScripts();
}
