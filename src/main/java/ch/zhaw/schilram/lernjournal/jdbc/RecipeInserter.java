package ch.zhaw.schilram.lernjournal.jdbc;

import ch.zhaw.schilram.lernjournal.domain.Ingredient;
import ch.zhaw.schilram.lernjournal.domain.Recipe;
import ch.zhaw.schilram.lernjournal.domain.StorableItem;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author schilram
 */
public class RecipeInserter extends AbstractInserter {


    @Override
    protected PreparedStatement createInsertStatement(final StorableItem toInsert) {

        PreparedStatement preparedStatement = null;
        Recipe recipe = null;

        if (toInsert instanceof Recipe) {
            recipe = (Recipe) toInsert;
        }

        String query = "INSERT INTO recipes"
                + "(name, description, instructions) VALUES"
                + "(?,?,?)";


        try {
            preparedStatement = dbConnection.prepareStatement(query);

            preparedStatement.setString(1, recipe.getName());
            preparedStatement.setString(2, recipe.getDescription());
            preparedStatement.setString(3, recipe.getInstruction());

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return preparedStatement;
    }

}
