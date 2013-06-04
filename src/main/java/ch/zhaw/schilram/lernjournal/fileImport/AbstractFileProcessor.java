package ch.zhaw.schilram.lernjournal.fileImport;

import java.io.File;

/**
 * @author schilram
 */
public abstract class AbstractFileProcessor implements Runnable {

    File file;

    protected AbstractFileProcessor(final File file) {
        this.file = file;
    }


}
