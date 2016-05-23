package DAO;

import org.apache.log4j.Logger;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Carlos Zubiran on 5/13/2016.
 */
public abstract class MySQL {

    //region PROPERTIES

    protected static String dbHost = "localhost";
    protected static String dbName = "oopFinal";
    protected static String dbUser = "consoleUser";
    protected static String dbPass = "qwe123$!";
    protected static String useSSL = "false";
    protected static String procBod = "true";
    protected static String zeroDateTimeBehavior = "convertToNull";

    protected static Connection connection = null;

    final static Logger logger = Logger.getLogger(MySQL.class);
    protected static final int GET_BY_ID = 10;
    protected static final int GET_COLLECTION = 20;
    protected static final int INSERT = 10;
    protected static final int UPDATE = 20;
    protected static final int DELETE = 30;

    //endregion PROPERTIES

    //region CONSTRUCTORS


    //endregion CONSTRUCTORS

    //region GETTERS / SETTERS


    //endregion GETTERS / SETTERS

    //region CUSTOM METHODS

    protected static void Connect() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            logger.error("MySQL Driver not found! " + ex);
        }

        logger.info("MySQL Driver Registered.");

        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + dbHost + ":3306/" + dbName
                    + "?zeroDateTimeBehavior=" + zeroDateTimeBehavior
                    + "&useSSL=" + useSSL + "&noAccessToProcedureBodies=" + procBod, dbUser, dbPass);
        } catch (SQLException ex) {
            logger.error("Connection failed!" + ex);
        }

        if (connection != null) {
            logger.info("Successfully connected to MySQL database");
        } else {
            logger.info("Connection failed!");
        }
    }

    public static void TruncateTables() {
        Connect();
        try {
            connection.createStatement().execute("call usp_DeleteStuffFromTable;");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //endregion CUSTOM METHODS

}
