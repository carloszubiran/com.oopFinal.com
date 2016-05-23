import DAO.MySQL;
import console.Console;
import org.apache.log4j.Logger;

public class Main {

    // making a variable to the org.apache.log4j.Logger;
    // to make sure that we can log all of the essential
    // info and errors
    public static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {

        Console aConsole = new Console();
        aConsole.greetUser();
        aConsole.DisplayOptionsForUsers();


    }
}



