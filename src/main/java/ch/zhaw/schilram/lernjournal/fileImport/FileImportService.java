package ch.zhaw.schilram.lernjournal.fileImport;

/**
 * @author schilram
 */
public interface FileImportService {

    String TMP_PATH = "c:\\tmp";

    /**
     * imports all known files form a directory.
     * @param path Path to import from
     */
    void importAllFromDirectory(final String path);

    /**
     * imports all known files form Temp directory.
     */
    void importAllFromTmp();

    /**
     * imports all Ingredient files form a directory.
     * @param path Path to import from
     */
    void importIngredientsFromDirectory(final String path);

    /**
     * imports all Ingredient files form Temp directory.
     */
    void importIngredientsFromTmp();

    /**
     * imports all Recipes files form a directory.
     * @param path Path to import from
     */
    void importRecipesFromDirectory(final String path);

    /**
     * imports all Recipes files form Temp directory.
     */
    void importRecipesFromTmp();

}
