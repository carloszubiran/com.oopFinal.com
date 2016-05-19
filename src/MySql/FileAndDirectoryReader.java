package MySql;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;

/**
 * Created by Carlos Zubiran on 5/19/2016.
 */
public class FileAndDirectoryReader {

    //region PROPERTIES

    final static Logger logger = Logger.getLogger(MySQL.class);

    //endregion PROPERTIES

    //region CONSTRUCTORS


    //endregion CONSTRUCTORS

    //region GETTERS / SETTERS


    //endregion GETTERS / SETTERS

    //region CUSTOM METHODS

    public static void getAStringAndAgregateFileAndFolders(File dir) {

        try {
            // Make an array of File objects
            File[] files = dir.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    //notes:    recursion happens here
                    logger.info("directory: " + file.getCanonicalPath());
                    getAStringAndAgregateFileAndFolders(file);
                } else {
                    logger.info("   file: " + file.getCanonicalPath());
                }
            }
        }
        catch (IOException ioEx) {
            logger.error(ioEx);
        }

    }
    //endregion CUSTOM METHODS


}
