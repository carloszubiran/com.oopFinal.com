package MySql;

import java.util.Scanner;

/**
 * Created by Carlos Zubiran on 5/18/2016.
 */
public class console {

    //region PROPERTIES

    private Scanner aScanner = new Scanner(System.in);
    private

    //endregion PROPERTIES

    //region CONSTRUCTORS


    //endregion CONSTRUCTORS

    //region GETTERS / SETTERS


    //endregion GETTERS / SETTERS

    //region CUSTOM METHODS

    static void DisplayOptionsForUsers() {
        // prompt the user to enter a filepath/directory
        System.out.println("Welcome, please enter a filepath/directory you would like get a list for.");

        // prompt the user enter an options of what to do after the filepath has been scanned and displayed

        System.out.println("i. Display directory with most files" +
                "\n ii. Display directory largest in size" +
                "\n iii. Display 5 largest files in size" +
                "\n iv. Display all files of a certain type" +
                "\n v. Clear the db and start over" +
                "\n vi. Exit"
        );


    }

    private String getTheOptionsFromTheUser() {


        int choice = 0;
        switch (choice) {
            case 1:

        }

        return aScanner.nextLine();
    }




    //endregion CUSTOM METHODS


}
