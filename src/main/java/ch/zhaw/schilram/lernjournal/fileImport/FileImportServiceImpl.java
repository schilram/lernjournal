package ch.zhaw.schilram.lernjournal.fileImport;

import java.io.File;
import java.util.List;

/**
 * @author schilram
 */
public class FileImportServiceImpl implements FileImportService {

    @Override
    public void importAllFromTmp() {
        importAllFromDirectory(TMP_PATH);
    }

    @Override
    public void importAllFromDirectory(final String path) {
        // To implement
    }

    @Override
    public void importIngredientsFromTmp() {
        importIngredientsFromDirectory(TMP_PATH);
    }

    @Override
    public void importIngredientsFromDirectory(final String path) {

        final List<File> files = getFiles(path);

        for (File file : files) {
            if (file.isFile() && file.getName().endsWith(".ingredient")) {
                final IngredientFileProcessor ingredientFileProcessor = new IngredientFileProcessor(file);
                ingredientFileProcessor.run();
            }
        }
    }

    private List<File> getFiles(final String path) {
        final DirectoryScanner scanner = new DirectoryScanner(path);

        return scanner.getFiles();
    }
}
