package ch.zhaw.schilram.lernjournal.web.controller;

import ch.zhaw.schilram.lernjournal.domain.Person;
import ch.zhaw.schilram.lernjournal.service.PersonService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;


/**
 * Handles and retrieves person request.
 */
@Controller
@RequestMapping("/main")
public class MainController {

    protected static Logger logger = Logger.getLogger("controller");

    @Resource(name="personService")
    private PersonService personService;

    /**
     * Handles and retrieves all persons and show it in a JSP page.
     *
     * @return the name of the JSP page
     */
    @RequestMapping(value = "/persons", method = RequestMethod.GET)
    public String getPersons(Model model) {

        logger.debug("Received request to show all persons");

        // Retrieve all persons by delegating the call to PersonService
        List<Person> persons = personService.getAll();

        // Attach persons to the Model
        model.addAttribute("persons", persons);

        // This will resolve to /WEB-INF/jsp/personspage.jsp
        return "personspage";
    }

    /**
     * Adds a new person by delegating the processing to PersonService.
     * Displays a confirmation JSP page
     *
     * @return  the name of the JSP page
     */
    @RequestMapping(value = "/persons/add", method = RequestMethod.GET)
    public String add(
            @RequestParam(value="firstname", required=true) String firstName,
            @RequestParam(value="lastname", required=true) String lastName,
            @RequestParam(value="money", required=true) Integer money) {

        logger.debug("Received request to add new person");

        // Call PersonService to do the actual adding
        personService.add(firstName, lastName, money);

        // This will resolve to /WEB-INF/jsp/addedpage.jsp
        return "addedpage";
    }

    /**
     * Deletes an existing person by delegating the processing to PersonService.
     * Displays a confirmation JSP page
     *
     * @return  the name of the JSP page
     */
    @RequestMapping(value = "/persons/delete", method = RequestMethod.GET)
    public String delete(@RequestParam(value="id", required=true) Integer id, Model model) {

        logger.debug("Received request to delete existing person");

        // Call PersonService to do the actual deleting
        personService.delete(id);

        // Add id reference to Model
        model.addAttribute("id", id);

        // This will resolve to /WEB-INF/jsp/deletedpage.jsp
        return "deletedpage";
    }

    /**
     * Edits an existing person by delegating the processing to PersonService.
     * Displays a confirmation JSP page
     *
     * @return  the name of the JSP page
     */
    @RequestMapping(value = "/persons/edit", method = RequestMethod.GET)
    public String edit(
            @RequestParam(value="id", required=true) Integer id,
            @RequestParam(value="firstname", required=true) String firstName,
            @RequestParam(value="lastname", required=true) String lastName,
            @RequestParam(value="money", required=true) Integer money,
            Model model) {

        logger.debug("Received request to edit existing person");

        // Call PersonService to do the actual editing
        personService.edit(id, firstName, lastName, money);

        // Add id reference to Model
        model.addAttribute("id", id);

        // This will resolve to /WEB-INF/jsp/editedpage.jsp
        return "editedpage";
    }
}
