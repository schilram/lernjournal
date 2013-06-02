package ch.zhaw.schilram.lernjournal.service;

import ch.zhaw.schilram.lernjournal.domain.Person;
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
 * Service for processing Persons. 
 * <p>
 * For a complete reference to Spring JDBC and JdbcTemplate
 * see http://static.springsource.org/spring/docs/3.0.x/spring-framework-reference/html/jdbc.html
 * <p>
 * For transactions, see http://static.springsource.org/spring/docs/3.0.x/spring-framework-reference/html/transaction.html
 */
@Service("personService")
@Transactional
public class PersonService {

	protected static Logger logger = Logger.getLogger("service");
	
	private SimpleJdbcTemplate jdbcTemplate;
	
	@Resource(name="dataSource")
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new SimpleJdbcTemplate(dataSource);
	}

	/**
	 * Retrieves all persons
	 * 
	 * @return a list of persons
	 */
	public List<Person> getAll() {
		logger.debug("Retrieving all persons");
		
		// Prepare our SQL statement
		String sql = "select id, firstname, lastname from person";
		
		// Maps a SQL result to a Java object
		RowMapper<Person> mapper = new RowMapper<Person>() {
	        public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	Person person = new Person();
	        	person.setId(rs.getInt("id"));
	        	person.setFirstName(rs.getString("firstname"));
	        	person.setLastName(rs.getString("lastname"));
//	        	person.setMoney(rs.getDouble("money"));
	            return person;
	        }
	    };
		
		// Retrieve all
		return jdbcTemplate.query(sql, mapper);
	}
	
	/**
	 * Adds a new person
	 * 
	 * @param firstName the first name of the person
	 * @param lastName the last name of the person
	 * @param money the money of the person
	 */
	public void add(String firstName, String lastName, Double money) {
		logger.debug("Adding new person");

		// Prepare our SQL statement using Named Parameters style
		String sql = "insert into person(first_name, last_name, money) values " +
				"(:firstName, :lastName, :money)";
		
		// Assign values to parameters
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("firstName", firstName);
		parameters.put("lastName", lastName);
		parameters.put("money", money);
		
		// Save
		jdbcTemplate.update(sql, parameters);
	}
	
	/**
	 * Deletes an existing person
	 * @param id the id of the existing person
	 */
	public void delete(Integer id) {
		logger.debug("Deleting existing person");
		
		// Prepare our SQL statement using Unnamed Parameters style
		String sql = "delete from person where id = ?";
		
		// Assign values to parameters
		Object[] parameters = new Object[] {id};
		
		// Delete
		jdbcTemplate.update(sql, parameters);
	}
	
	/**
	 * Edits an existing person
	 * @param id the id of the existing person
	 * @param firstName the first name of the existing person
	 * @param lastName the last name of the existing person
	 * @param money the money of the existing person
	 */
	public void edit(Integer id, String firstName, String lastName, Double money) {
		logger.debug("Editing existing person");
		
		// Prepare our SQL statement
		String sql = "update person set first_name = :firstName, " +
				"last_name = :lastName, money = :money where id = :id";
		
		// Assign values to parameters
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("id", id);
		parameters.put("firstName", firstName);
		parameters.put("lastName", lastName);
		parameters.put("money", money);
		
		// Edit
		jdbcTemplate.update(sql, parameters);
		
	}
}
