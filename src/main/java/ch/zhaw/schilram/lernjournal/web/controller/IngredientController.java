package ch.zhaw.schilram.lernjournal.web.controller;

import ch.zhaw.schilram.lernjournal.domain.Ingredient;
import ch.zhaw.schilram.lernjournal.web.service.IngredientService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;


/**
 * Handles and retrieves ingredient request.
 *
 * @author schilram
 */
@Controller
@RequestMapping("/ingredients")
public class IngredientController {

    private static Logger logger = Logger.getLogger("controller");

    @Resource(name = "ingredientService")
    private IngredientService ingredientService;

    /**
     * Handles and retrieves all ingredients and show it in a JSP page.
     *
     * @return the name of the JSP page
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getIngredients(final Model model) {

        logger.debug("Received request to show all ingredients");

        // Retrieve all ingredients by delegating the call to IngredientService
        final List<Ingredient> ingredients = ingredientService.getAll();

        // Attach persons to the Model
        model.addAttribute("ingredients", ingredients);

        // This will resolve to /WEB-INF/jsp/ingredients/list.jsp
        return "ingredients/list";
    }

    /**
     * Adds a new ingredient by delegating the processing to IngredientService.
     * Displays a confirmation JSP page
     *
     * @return  the name of the JSP page
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(
            @RequestParam(value = "name", required = true) final String name,
            @RequestParam(value = "description", required = false) final  String description,
            @RequestParam(value = "flavour", required = false) final  String flavour) {

        logger.debug("Received request to add new ingredient");

        // Call IngredientService to do the actual adding
        ingredientService.add(name, description, flavour);

        // This will resolve to /WEB-INF/jsp/ingredients/addedpage.jsp
        return "ingredients/addedpage";
    }

    /**
     * Deletes an existing ingredient by delegating the processing to IngredientService.
     * Displays a confirmation JSP page
     *
     * @return  the name of the JSP page
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam(value = "id", required = true) final Integer id, final Model model) {

        logger.debug("Received request to delete existing ingredient");

        // Call IngredientService to do the actual deleting
        ingredientService.delete(id);

        // Add id reference to Model
        model.addAttribute("id", id);

        // This will resolve to /WEB-INF/jsp/ingredients/deletedpage.jsp
        return "ingredients/deletedpage";
    }

    /**
     * Edits an existing ingredient by delegating the processing to IngredientService.
     * Displays a confirmation JSP page
     *
     * @return  the name of the JSP page
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(
            @RequestParam(value = "id", required = true) final Integer id,
            @RequestParam(value = "name", required = true) final String name,
            @RequestParam(value = "description", required = false) final String description,
            @RequestParam(value = "flavour", required = false) final  String flavour,
            final Model model) {

        logger.debug("Received request to edit existing ingredient");

        // Call IngredientService to do the actual editing
        ingredientService.edit(id, name, description, flavour);

        // Add id reference to Model
        model.addAttribute("id", id);

        // This will resolve to /WEB-INF/jsp/ingredients/editedpage.jsp
        return "ingredients/editedpage";
    }

}
