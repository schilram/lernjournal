package ch.zhaw.schilram.lernjournal.web.controller;

import ch.zhaw.schilram.lernjournal.fileImport.FileImportServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Handles and retrieves person request.
 */
@Controller
@RequestMapping("/")
public class RootController {

    protected static Logger logger = Logger.getLogger("rootController");

    /**
     *
     * @return the name of the JSP page
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showIndex() {

        logger.debug("Received request to show index");

        // This will resolve to /WEB-INF/jsp/index.jsp
        return "/index";
    }

    @RequestMapping(value = "/import/ingredientsFromTmp", method = RequestMethod.GET)
    public String importFromTmp() {
        logger.debug("Start Import from tmp dir");

        final FileImportServiceImpl fis = new FileImportServiceImpl();
        fis.importIngredientsFromTmp();

        // This will resolve to /WEB-INF/jsp/import/scanned.jsp
        return "/import/scanned";
    }
}
