package ch.zhaw.schilram.lernjournal.fileImport;

import ch.zhaw.schilram.lernjournal.domain.Ingredient;
import ch.zhaw.schilram.lernjournal.domain.Recipe;
import ch.zhaw.schilram.lernjournal.exceptions.NoIngredientFileException;
import ch.zhaw.schilram.lernjournal.exceptions.NoRecipeFileException;
import ch.zhaw.schilram.lernjournal.jdbc.IngredientInserter;
import ch.zhaw.schilram.lernjournal.jdbc.RecipeInserter;
import ch.zhaw.schilram.lernjournal.web.service.IngredientService;

import java.io.*;

/**
 * Reads a File and Stores the Ingredient.
 * Any following lines are ignored.
 *
 * @author schilram
 */
public class RecipeFileProcessor extends AbstractFileProcessor {

    public RecipeFileProcessor(final File file) {
        super(file);
    }

    private Recipe readFile() throws NoRecipeFileException {
        final Recipe recipe = new Recipe();

        try {
            final BufferedReader reader = new BufferedReader(new FileReader(file));
            final String line = reader.readLine();

            final String[] parts = line.split(";");
            if (parts.length > 3) {
                throw new NoRecipeFileException();
            }

            String name = "";
            String description = "";
            String instructions = "";

            name = parts[0].trim();
            if (parts.length > 1) {
                description = parts[1].trim();
            }
            if (parts.length > 2) {
                instructions = parts[2].trim();
            }

            if (name.equals("")) {
                throw new NoRecipeFileException();
            }
            recipe.setName(name);
            recipe.setDescription(description);
            recipe.setInstruction(instructions);

            reader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return recipe;
    }

    @Override
    public void run() {
        try {
            final Recipe recipe = readFile();
            final RecipeInserter inserter = new RecipeInserter();
            final boolean inserted = inserter.insert(recipe);

            if (inserted) {
                file.delete();
            }

        } catch (NoRecipeFileException e) {
            e.printStackTrace();
        }
    }
}
