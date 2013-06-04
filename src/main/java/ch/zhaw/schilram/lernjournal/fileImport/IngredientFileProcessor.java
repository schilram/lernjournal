package ch.zhaw.schilram.lernjournal.fileImport;

import ch.zhaw.schilram.lernjournal.domain.Ingredient;
import ch.zhaw.schilram.lernjournal.exceptions.NoIngredientFileException;
import ch.zhaw.schilram.lernjournal.jdbc.IngredientInserter;
import ch.zhaw.schilram.lernjournal.web.service.IngredientService;
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
            if (parts.length > 3) {
                throw new NoIngredientFileException();
            }

            String name = "";
            String description = "";
            Ingredient.Flavour flavour = null;

            name = parts[0].trim();
            if (parts.length > 1) {
                description = parts[1].trim();
            }
            if (parts.length > 2) {

                String f = parts[2].trim().toUpperCase();
                if (f.equals(Ingredient.Flavour.SALTY.name())) {
                    flavour = Ingredient.Flavour.SALTY;
                } else if (f.equals(Ingredient.Flavour.SWEET.name())) {
                    flavour = Ingredient.Flavour.SWEET;
                } else if (f.equals(Ingredient.Flavour.SOUR.name())) {
                    flavour = Ingredient.Flavour.SOUR;
                } else if (f.equals(Ingredient.Flavour.BITTER.name())) {
                    flavour = Ingredient.Flavour.BITTER;
                } else if (f.equals(Ingredient.Flavour.UMAMI.name())) {
                    flavour = Ingredient.Flavour.UMAMI;
                }
            }

            if (name.equals("")) {
                throw new NoIngredientFileException();
            }
            ingredient.setName(name);
            ingredient.setDescription(description);
            ingredient.setFlavour(flavour);

            reader.close();

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
            final IngredientInserter inserter = new IngredientInserter();
            final boolean inserted = inserter.insert(ingredient);

            if (inserted) {
                file.delete();
            }

        } catch (NoIngredientFileException e) {
            e.printStackTrace();
        }
    }
}
