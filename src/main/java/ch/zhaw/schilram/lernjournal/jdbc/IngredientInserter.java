package ch.zhaw.schilram.lernjournal.jdbc;

import ch.zhaw.schilram.lernjournal.domain.Ingredient;
import ch.zhaw.schilram.lernjournal.domain.StorableItem;

import java.sql.*;

/**
 * @author schilram
 */
public class IngredientInserter extends AbstractInserter {


    @Override
    protected PreparedStatement createInsertStatement(final StorableItem toInsert) {

        PreparedStatement preparedStatement = null;
        Ingredient ingredient = null;

        if (toInsert instanceof Ingredient) {
            ingredient = (Ingredient) toInsert;
        }

        String query = "INSERT INTO ingredients"
                + "(name, description, flavour) VALUES"
                + "(?,?,?)";


        try {
            preparedStatement = dbConnection.prepareStatement(query);

            preparedStatement.setString(1, ingredient.getName());
            preparedStatement.setString(2, ingredient.getDescription());
            if (ingredient.getFlavour() != null) {
                preparedStatement.setString(3, ingredient.getFlavour().name());
            } else {
                preparedStatement.setString(3, "");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return preparedStatement;
    }

}
