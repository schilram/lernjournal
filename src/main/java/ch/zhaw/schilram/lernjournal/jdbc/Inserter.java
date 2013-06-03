package ch.zhaw.schilram.lernjournal.jdbc;

import ch.zhaw.schilram.lernjournal.domain.StorableItem;

import java.sql.PreparedStatement;

/**
 * @author: rschilling
 */
public interface Inserter<T extends StorableItem> {

    PreparedStatement createInsertStatement(final T toInsert);
}
