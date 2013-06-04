package ch.zhaw.schilram.lernjournal.web.controller;

import ch.zhaw.schilram.lernjournal.domain.Ingredient;
import ch.zhaw.schilram.lernjournal.domain.Recipe;
import ch.zhaw.schilram.lernjournal.web.service.IngredientService;
import ch.zhaw.schilram.lernjournal.web.service.RecipeService;
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
@RequestMapping("/recipes/")
public class RecipeController {

    private static Logger logger = Logger.getLogger("controller");

    @Resource(name = "recipeService")
    private RecipeService recipeService;

    /**
     * Handles and retrieves all recipes and show it in a JSP page.
     *
     * @return the name of the JSP page
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getRecipes(final Model model) {

        logger.debug("Received request to show all ingredients");

        // Retrieve all recipes by delegating the call to RecipeService
        final List<Recipe> recipes = recipeService.getAll();

        // Attach persons to the Model
        model.addAttribute("recipes", recipes);

        // This will resolve to /WEB-INF/jsp/ingredients/list.jsp
        return "recipes/list";
    }

}
