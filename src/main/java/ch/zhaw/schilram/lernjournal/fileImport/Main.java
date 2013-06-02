package ch.zhaw.schilram.lernjournal.fileImport;

/**
 * @author: rschilling
 */
public class Main {

    public static void main(final String[] args) {
        DirectoryScanner scanner = new DirectoryScanner("c:\\tmp");

        scanner.scanDirectory();
    }
}
