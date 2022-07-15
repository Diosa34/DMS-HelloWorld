import com.github.diosa.dms.SameLinksException;
import com.github.diosa.dms.mainGUI.Alert;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;

/**
 * Collection of paths to currently executing scripts
 */
public class HistoryOfExecutingScripts extends LinkedList<Path> {
    public static HistoryOfExecutingScripts collectionOfFiles = new HistoryOfExecutingScripts();

    public static void addScript(String filepath) throws IOException {
        try {
            if (!isSameLinks(new File(filepath).toPath())) {
                collectionOfFiles.add(new File(filepath).toPath());
            }
        } catch(SameLinksException e){
            Alert.error(SameLinksException.message);
        }
    }

    /**
     * Link Identity verification
     */
    public static boolean isSameLinks(Path filePath) throws IOException, SameLinksException {
        for (Path path : HistoryOfExecutingScripts.collectionOfFiles) {
            if (Files.isSameFile(filePath, path)) {
                throw new SameLinksException();
            }
        }
        return false;
    }

    public static void removeScript(){
        collectionOfFiles.removeLast();
    }
}