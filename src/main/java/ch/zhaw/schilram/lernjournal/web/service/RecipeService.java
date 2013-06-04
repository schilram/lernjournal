package ch.zhaw.schilram.lernjournal.web.service;

import ch.zhaw.schilram.lernjournal.domain.Recipe;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author schilram
 */
@Service("recipeService")
@Transactional
public class RecipeService {

    private static Logger logger = Logger.getLogger("service");

    private SimpleJdbcTemplate jdbcTemplate;

    @Resource(name = "dataSource")
    public void setDataSource(final DataSource dataSource) {
        this.jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }

    /**
     * Retrieves all recipes.
     *
     * @return a list of recipes
     */
    public List<Recipe> getAll() {
        logger.debug("Retrieving all ingredients");

        // Prepare our SQL statement
        String sql = "select id, name, description, instructions from recipes";

        // Maps a SQL result to a Java object
        RowMapper<Recipe> mapper = new RowMapper<Recipe>() {
            public Recipe mapRow(final ResultSet rs, final int rowNum) throws SQLException {
                final Recipe recipe = new Recipe();
                recipe.setId(rs.getInt("id"));
                recipe.setName(rs.getString("name"));
                recipe.setDescription(rs.getString("description"));
                recipe.setInstructions(rs.getString("instructions"));

                return recipe;
            }
        };

        // Retrieve all
        return jdbcTemplate.query(sql, mapper);
    }

}
