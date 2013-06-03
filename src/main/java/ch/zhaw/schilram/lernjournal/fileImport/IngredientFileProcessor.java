package ch.zhaw.schilram.lernjournal.fileImport;

import ch.zhaw.schilram.lernjournal.domain.Ingredient;
import ch.zhaw.schilram.lernjournal.exceptions.FileNotDeletedException;
import ch.zhaw.schilram.lernjournal.exceptions.NoIngredientFileException;
import ch.zhaw.schilram.lernjournal.service.IngredientService;
import org.apache.log4j.Logger;

import java.io.*;

/**
 * Reads a File and Stores the Ingredient.
 * Any following lines are ignored.
 *
 * @author schilram
 */
public class IngredientFileProcessor extends AbstractFileProcessor {

    public static final Logger LOGGER = Logger.getLogger("ingredientFileReader");


    private final IngredientService service = new IngredientService();
    private final Ingredient ingredient = new Ingredient();


    public IngredientFileProcessor(final File file) {
        super(file);
    }

    private Ingredient readFile() throws NoIngredientFileException {
        final Ingredient ingredient = new Ingredient();

        try {
            LOGGER.debug("Reading File");
            final BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();

            final String[] parts = line.split(";");
            if (parts.length > 2) {
                throw new NoIngredientFileException();
            }

            String name = "";
            String description = "";

            name = parts[0].trim();
            if (parts.length == 2) {
                description = parts[1].trim();
            }

            if (name.equals("")) {
                throw new NoIngredientFileException();
            }
            ingredient.setName(name);
            ingredient.setDescription(description);

        } catch (FileNotFoundException e) {
            LOGGER.debug("FileNotFoundException");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ingredient;
    }

    @Override
    public void run() {
        try {
            final Ingredient ingredient = readFile();
            IngredientAdder.insert(ingredient);
//            service.add(ingredient.getName(), ingredient.getDescription());

            // Delete File
            if (! file.canWrite()) {
                throw new IllegalArgumentException("Delete: write proteced: " + file.getName());
            }
            boolean success = file.delete();
            file.renameTo(new File(file.getName() + ".inserted"));
            if (!success) {

            }

        } catch (NoIngredientFileException e) {
            e.printStackTrace();
        }
    }
}
