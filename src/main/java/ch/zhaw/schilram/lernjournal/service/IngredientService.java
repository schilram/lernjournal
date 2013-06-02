package ch.zhaw.schilram.lernjournal.service;

import ch.zhaw.schilram.lernjournal.domain.Ingredient;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author schilram
 */
@Service("ingredientService")
@Transactional
public class IngredientService {

    private static Logger logger = Logger.getLogger("service");

    private SimpleJdbcTemplate jdbcTemplate;

    @Resource(name = "dataSource")
    public void setDataSource(final DataSource dataSource) {
        this.jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }

    /**
     * Retrieves all ingredients.
     *
     * @return a list of ingredients
     */
    public List<Ingredient> getAll() {
        logger.debug("Retrieving all ingredients");

        // Prepare our SQL statement
        String sql = "select id, name, description from ingredients";

        // Maps a SQL result to a Java object
        RowMapper<Ingredient> mapper = new RowMapper<Ingredient>() {
            public Ingredient mapRow(final ResultSet rs, final int rowNum) throws SQLException {
                final Ingredient ingredient = new Ingredient();
                ingredient.setId(rs.getInt("id"));
                ingredient.setName(rs.getString("name"));
                ingredient.setDescription(rs.getString("description"));

                return ingredient;
            }
        };

        // Retrieve all
        return jdbcTemplate.query(sql, mapper);
    }

    /**
     * Adds a new ingredient.
     *
     * @param name the name of the ingredient
     * @param description the last description of the ingredient
     */
    public void add(final String name, final String description) {
        logger.debug("Adding new ingredient");

        // Prepare our SQL statement using Named Parameters style
        String sql = "insert into ingredients(name, description) values (:name, :description)";

        // Assign values to parameters
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("name", name);
        parameters.put("description", description);

        // Save
        jdbcTemplate.update(sql, parameters);
    }

    /**
     * Deletes an existing ingredient.
     * @param id the id of the existing ingredient
     */
    public void delete(final Integer id) {
        logger.debug("Deleting existing ingredient");

        // Prepare our SQL statement using Unnamed Parameters style
        final String sql = "delete from ingredients where id = ?";

        // Assign values to parameters
        final Object[] parameters = new Object[] {id};

        // Delete
        jdbcTemplate.update(sql, parameters);
    }

    /**
     * Edits an existing ingredient.
     * @param id the id of the existing ingredient
     * @param name the name of the existing ingredient
     * @param description the description of the existing ingredient
     */
    public void edit(final Integer id, final String name, final String description) {
        logger.debug("Editing existing ingredient");

        // Prepare our SQL statement
        final String sql = "update ingredients set name = :name, description= :description where id = :id";

        // Assign values to parameters
        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id", id);
        parameters.put("name", name);
        parameters.put("description", description);

        // Edit
        jdbcTemplate.update(sql, parameters);

    }

}
