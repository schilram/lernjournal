package ch.zhaw.schilram.lernjournal.fileImport;

import org.apache.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author schilram
 */
public class DirectoryScanner {

    public static final Logger LOGGER = Logger.getLogger("directoryScanner");

    private File directory;

    public DirectoryScanner(final String directory) {
        this.directory = new File(directory);
    }


    public void scanDirectory() {

        final String[] directoryContents = directory.list();
        final List<File> files = new ArrayList<File>();

        for (String fileName : directoryContents) {
            final File temp = new File(String.valueOf(directory), fileName);
            files.add(temp);
        }

        for (File file : files) {
            if (file.isFile() && file.getName().endsWith(".ingredient")) {
                final IngredientFileProcessor ingredientFileProcessor = new IngredientFileProcessor(file);
                ingredientFileProcessor.run();
            }
        }
    }

}
