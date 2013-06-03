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

    public File getDirectory() {
        return directory;
    }

    public void setDirectory(final File directory) {
        this.directory = directory;
    }

    /**
     * scans the directory.
     * @return files List with all Files in the scanned directory
     */
    public List<File> getFiles() {
        final String[] directoryContents = directory.list();
        final List<File> files = new ArrayList<File>();

        for (String fileName : directoryContents) {
            final File temp = new File(String.valueOf(directory), fileName);
            files.add(temp);
        }

        return files;

    }

}
