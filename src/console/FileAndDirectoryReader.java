package console;

import DAO.DirectoryDAOImpl;
import DAO.FileDAOImpl;
import DAO.Implementations.DirectoryDAO;
import DAO.Implementations.FileDAO;
import DAO.MySQL;
import FO.Directory;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;

/**
 * Created by Carlos Zubiran on 5/19/2016.
 */
public class FileAndDirectoryReader {

    //region PROPERTIES

    // a logger to log stuff
    final static Logger logger = Logger.getLogger(MySQL.class);

    //endregion PROPERTIES

    //region CONSTRUCTORS


    public FileAndDirectoryReader() {
    }


    // Constructor that takes a file as an argument
    public FileAndDirectoryReader(File aFile) {
        getFileObjectAndDisplayFileAndFolders(aFile);
    }


    //endregion CONSTRUCTORS

    //region GETTERS / SETTERS



    //endregion GETTERS / SETTERS

    //region CUSTOM METHODS

    // converts a full name of a file into a extension only name
    private static String convertFullNameToJustFileExtension(File file) {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }

    // this is the method that will recursively traverse the filesystem
    // it also puts these objects into the database, they can be called
    // on later after they have been inserted.
    // The sizes of the directories and the amount of files of the
    // directories are updated on the database end using store procedures

    public void getFileObjectAndDisplayFileAndFolders(File dir) {

        try {

            // int for current directory
            int currentDir = 0;
            // make a new directory object
            Directory aDirObject = new Directory();
            // make a new directory data access (interface) object with it's implementation (class that implements it)
            DirectoryDAO directoryDAO = new DirectoryDAOImpl();
            // create a new file object
            FO.File aFileObject = new FO.File();
            // make a new file data access (interface) object with it's implementation (class that implements it)
            FileDAO fileDAO = new FileDAOImpl();
            // An array of files
            File[] files = dir.listFiles();

            for (File file : files) {
                if (file.isDirectory()) {
                    //notes:    recursion happens here

                    // set values for the directory objects
                    aDirObject.setDirectoryName(file.getName());
                    aDirObject.setPath(file.getCanonicalPath());

                    // this code completes the insert and gets the current auto increment index
                    // of the database and store it to currentDir
                    currentDir = directoryDAO.insertDirectory(aDirObject);

                    // display the path of the current directory
                    logger.info("directory: " + file.getCanonicalPath());

                    // do a recursive call
                    getFileObjectAndDisplayFileAndFolders(file);
                } else {
                    // this sets the attributes of the file objects
                    aFileObject.setFileName(file.getName());
                    aFileObject.setFileType(convertFullNameToJustFileExtension(file));
                    aFileObject.setFileSize((int)file.length());
                    aFileObject.setPath(file.getPath());
                    aFileObject.setFK_DirectoryId(currentDir);

                    // insert the file into the database
                    fileDAO.insertFile(aFileObject);
                    logger.info("   file: " + file.getCanonicalPath());
                }
            }

            // when all the directories are added to the database,
            // this will update the directory and file count for all
            // the directories in the database.
            directoryDAO.updateDirectorySizesAndFileCount();

        }
        catch (IOException ioEx) {
            logger.error(ioEx);
        }

    }


    //endregion CUSTOM METHODS


}
