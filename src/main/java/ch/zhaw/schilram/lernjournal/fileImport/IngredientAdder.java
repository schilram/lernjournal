package ch.zhaw.schilram.lernjournal.fileImport;

import ch.zhaw.schilram.lernjournal.domain.Ingredient;
import ch.zhaw.schilram.lernjournal.jdbc.JdbcConnection;

import java.sql.*;

/**
 * @author schilram
 */
public class IngredientAdder {

//    public static void insert(final Ingredient ingredient) {
//        final Connection connection = JdbcConnection.getConnection();
//        final String query = "INSERT INTO ingredients(name, description) values ('" + ingredient.getName() + "', '" + ingredient.getDescription() + "')";
//
//        Statement statement = null;
//
//        try {
//            statement = connection.createStatement();
//            ResultSet rs = statement.executeQuery(query);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public static void insert(final Ingredient ingredient)  {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String query = "INSERT INTO ingredients"
                + "(name, description) VALUES"
                + "(?,?)";

        try {
            dbConnection = JdbcConnection.getConnection();
            preparedStatement = dbConnection.prepareStatement(query);

            preparedStatement.setString(1, ingredient.getName());
            preparedStatement.setString(2, ingredient.getDescription());
            
            // execute insert SQL stetement
            preparedStatement.executeUpdate();

            System.out.println("Record is inserted");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            JdbcConnection.closeConnection(dbConnection);
        }
    }

}
