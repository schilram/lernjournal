package ch.zhaw.schilram.lernjournal.jdbc;

import ch.zhaw.schilram.lernjournal.domain.StorableItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author schilram
 */
public abstract class AbstractInserter<T extends StorableItem> {

    protected Connection dbConnection;

    protected AbstractInserter() {
        dbConnection = JdbcConnection.getConnection();
    }

    public boolean insert(final T toInsert) {

        PreparedStatement preparedStatement = null;
        preparedStatement = createInsertStatement(toInsert);



        try {
            final int result;
            result = preparedStatement.executeUpdate();
            if (result == 1) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

    /**
     * Overwrite this method in Subclasses.
     * @return Insert Query String
     */
    protected abstract PreparedStatement createInsertStatement(final T toInsert);
}
