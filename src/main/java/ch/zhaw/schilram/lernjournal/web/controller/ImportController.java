package ch.zhaw.schilram.lernjournal.web.controller;

import ch.zhaw.schilram.lernjournal.fileImport.DirectoryScanner;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Handles and retrieves person request.
 */
@Controller
@RequestMapping("/")
public class ImportController {

    protected static Logger logger = Logger.getLogger("importController");

    /**
     *
     * @return the name of the JSP page
     */
    @RequestMapping(value = "/import/", method = RequestMethod.GET)
    public String showImport() {

        logger.debug("Received request to show import possibilities");

        // This will resolve to /WEB-INF/jsp/import/overview.jsp
        return "/import/overview";
    }

    @RequestMapping(value = "/import/tmp", method = RequestMethod.GET)
    public String importFromTmp() {
        logger.debug("Start Import from tmp dir");

        DirectoryScanner scanner = new DirectoryScanner("c:\\tmp");
        scanner.scanDirectory();

        // This will resolve to /WEB-INF/jsp/import/scanned.jsp
        return "/import/scanned";
    }
}
