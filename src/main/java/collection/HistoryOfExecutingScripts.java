package collection;

import java.nio.file.Path;
import java.util.LinkedList;

public class HistoryOfExecutingScripts extends LinkedList<Path> {
    public static HistoryOfExecutingScripts CollectionOfFiles = new HistoryOfExecutingScripts();
}
