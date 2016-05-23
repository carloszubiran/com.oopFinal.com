package console;

import DAO.DirectoryDAOImpl;
import DAO.FileDAOImpl;
import DAO.Implementations.DirectoryDAO;
import DAO.Implementations.FileDAO;
import DAO.MySQL;
import FO.Directory;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.*;

/**
 * Created by Carlos Zubiran on 5/18/2016.
 */
public class Console {

    //region PROPERTIES


    // make a logger for the class
    final static Logger logger = Logger.getLogger(Console.class);
    // make a scanner for input from the user
    private Scanner aScanner = new Scanner(System.in);
    // create a new file and directory reader
    private FileAndDirectoryReader aFileAndDirectoryReader;

    //endregion PROPERTIES

    //region CONSTRUCTORS


    //endregion CONSTRUCTORS

    //region GETTERS / SETTERS


    //endregion GETTERS / SETTERS

    //region CUSTOM METHODS

    public void greetUser() {
        // prompt the user to enter a filepath/directory
        System.out.println("Welcome, please enter a filepath / directory you would like get a list for.");


        try{
            aFileAndDirectoryReader = new FileAndDirectoryReader(getTheDirectoryFromUser());
        } catch (NullPointerException ex){
            this.enterCorrectFilePath();
        }
    }

    public void enterCorrectFilePath() {
        System.out.println("You entered an invalid file path / Directory, please try again");
        try{
            aFileAndDirectoryReader = new FileAndDirectoryReader(getTheDirectoryFromUser());
        } catch (NullPointerException ex){
            this.enterCorrectFilePath();
        }

    }


    public void DisplayOptionsForUsers() {
        // prompt the user enter an options of what to do after the filepath has been scanned and displayed

        System.out.println(
                "\n----------------------------------------\n" +
                "i. Display directory with most files\n" +
                "ii. Display directory largest in size\n" +
                "iii. Display 5 largest files in size\n" +
                "iv. Display all files of a certain type\n" +
                "v. Clear the db and start over\n" +
                "vi. Exit"
        );


        getTheOptionsFromTheUser(aScanner.nextLine());

    }


    // this method asks the user for a valid directory path then
    // creates a new java.file.io object and returns it
    public File getTheDirectoryFromUser() {
        return new File(aScanner.nextLine());
    }

    // after the user has the file system shown to him
    // that the user to a menu of options
    public void getTheOptionsFromTheUser(String selectionFromUser) {
        // create a new file and directory Data Access Object
        DirectoryDAO directoryDAO = new DirectoryDAOImpl();
        FileDAO fileDAO = new FileDAOImpl();
        // the choice from the user
        int choice = 0;

        // try to parse the string into a number, if it fails
        // display an error to the user and signal for another
        // entry.
        try {
             choice = Integer.parseInt(selectionFromUser);

        } catch (NumberFormatException ex) {
            System.out.println("Please enter a valid number 1 thought 6:");
            DisplayOptionsForUsers();
        }

        //create a directory object
        Directory dir = null;

        switch (choice) {
            // Display directory with the most files
            case 1:
                int numOfFiles = 0;
                // for every directory in the list
                // check to see if its greater that
                // previous directory, if it is,
                // update who into var dir and var numOfFiles

                for (Directory D : directoryDAO.getDirectoryList()){
                    if (D.getNumberOfFiles() >= numOfFiles) {
                        numOfFiles = D.getNumberOfFiles();
                        dir = D;
                    }

                }

                // Display the result to the user
                System.out.println("Directory: " + dir.getDirectoryName()
                + "has the most files with the total of: " + numOfFiles);

                // Return to menu
                DisplayOptionsForUsers();

                break;

            // Display the directory largest in size
            case 2:
                // same logic as before except for largest directory
                int sizeOfDirectory = 0;
                for (Directory D : directoryDAO.getDirectoryList()){

                    if (D.getNumberOfFiles() >= sizeOfDirectory) {
                        sizeOfDirectory = D.getDirectorySize();
                        dir = D;
                    }

                }

                // Display the result to the user
                System.out.println("Directory: " + dir.getDirectoryName()
                        + "has the largest file size of: " + sizeOfDirectory);

                // Return to menu
                DisplayOptionsForUsers();

                break;
            // Display 5 largest files in size
            case 3:

                // create a list of files
                List<FO.File> fileList = fileDAO.getFileList();
                // sort the files, file object needs to a compare to method
                // implemented

                // sort the list
                Collections.sort(fileList);
                // make a sub list then return the top five biggest files
                // counting from the to to the bottom
                fileList = fileList.subList(fileList.size() - 5, fileList.size());
                // printout the list
                System.out.println("This are the five biggest files: ");
                for (int i = 4; i >= 0 ; i--) {
                    System.out.println(fileList.get(i).getFileName() + " : " + fileList.get(i).getFileSize());

                }

                // Return to menu
                DisplayOptionsForUsers();

                break;

            // Display all files of a certain type
            case 4:

                // create a new list of files
                fileList = fileDAO.getFileList();
                // create a hash set with the size of the file list
                // for only unique string values
                HashSet<String> aHashSet = new HashSet(fileList.size());
                // put all list elements the hash set as file type string
                for (FO.File F : fileList) {
                    aHashSet.add(F.getFileType());
                }

                // print the all the unique elements in the hash set
                System.out.println("Here are all the unique file types");
                aHashSet.forEach(System.out::println);

                // Return to menu
                DisplayOptionsForUsers();

                break;
            // Clear the database and start over
            case 5:
                // delete all the data from the database. Any other queries
                // will cause an error and need to run the file recursion again.
                MySQL.TruncateTables();
                // Return to menu
                DisplayOptionsForUsers();
                break;
            // Exit the program and delete the data from the database
            default:
                MySQL.TruncateTables();
                break;


        }

    }




    //endregion CUSTOM METHODS


}
