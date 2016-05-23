package DAO;

import DAO.Implementations.DirectoryDAO;
import FO.Directory;
import Helper.PreparedStatementHelper;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Carlos Zubiran on 5/19/2016.
 */
public class DirectoryDAOImpl extends MySQL implements DirectoryDAO {

    //region PROPERTIES


    //endregion PROPERTIES

    //region CONSTRUCTORS


    //endregion CONSTRUCTORS

    //region GETTERS / SETTERS


    //endregion GETTERS / SETTERS

    //region CUSTOM METHODS

    @Override
    public Directory getDirectoryById(int directoryId) {
        Connect();
        Directory directory = null;   //not instantiated because if no records returned we don't want to use memory.

        try {
            CallableStatement cStmt = connection.prepareCall(
                    PreparedStatementHelper.callNameAndNumOfProcedures("usp_GetDirectory", 6));

            cStmt.setInt(1, GET_BY_ID);
            cStmt.setInt(2, directoryId);
            ResultSet rs = cStmt.executeQuery();

            if (rs.next()) {
                directory = HydrateObject(rs);
            }

            connection.close();

        } catch (SQLException ex) {
            logger.error(ex);
        }

        return directory;

    }

    @Override
    public List<Directory> getDirectoryList() {
        Connect();
        List<Directory> directoryList = new ArrayList<Directory>();
        try {
            CallableStatement cStmt = connection.prepareCall(
                    PreparedStatementHelper.callNameAndNumOfProcedures("usp_GetDirectory", 2));

            cStmt.setInt(1, GET_COLLECTION);
            cStmt.setInt(2,0);
            ResultSet rs = cStmt.executeQuery();

            while(rs.next()) {

                directoryList.add(HydrateObject(rs));
            }

            connection.close();

        } catch (SQLException ex) {
            logger.error(ex);
        }

        return directoryList;

    }

    @Override
    public int insertDirectory(Directory directory) {

        Connect();
        int id = 0;

        //region index values

        /*
        IN QueryId 			INT,
        IN vDirectoryId 	INT,
        IN DirectoryName	VARCHAR(1000),
        IN DirectorySize	INT,
        IN NumberOfFiles	INT,
        IN `Path`			VARCHAR(1000)
        * */

        //endregion

        try {
            CallableStatement cStmt = connection.prepareCall(
                    PreparedStatementHelper.callNameAndNumOfProcedures("usp_ExecuteDirectory", 6));

            cStmt.setInt(1, INSERT);
            cStmt.setInt(2, 0);
            cStmt.setString(3, directory.getDirectoryName());
            cStmt.setInt(4, directory.getDirectorySize());
            cStmt.setInt(5, directory.getNumberOfFiles());
            cStmt.setString(6, directory.getPath());

            ResultSet rs = cStmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }

            connection.close();

        } catch (SQLException sqlEx) {
            logger.error(sqlEx);
        }


        return id;
    }

    @Override
    public boolean updateDirectory(Directory directory) {
        Connect();
        int id = 0;

        //region index values

        /*
        IN QueryId 			INT,
        IN vDirectoryId 	INT,
        IN DirectoryName	VARCHAR(1000),
        IN DirectorySize	INT,
        IN NumberOfFiles	INT,
        IN `Path`			VARCHAR(1000)
        * */

        //endregion

        try {
            CallableStatement cStmt = connection.prepareCall(
                    PreparedStatementHelper.callNameAndNumOfProcedures("usp_ExecuteDirectory", 6));

            cStmt.setInt(1, UPDATE);                            // Changed from insert method
            cStmt.setInt(2, directory.getDirectoryId());              // Changed from insert method
            cStmt.setString(3, directory.getDirectoryName());
            cStmt.setInt(4, directory.getDirectorySize());
            cStmt.setInt(5, directory.getNumberOfFiles());
            cStmt.setString(6, directory.getPath());

            ResultSet rs = cStmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }

            connection.close();

        } catch (SQLException sqlEx) {
            logger.error(sqlEx);
        }
        return id > 0;
    }

    @Override
    public boolean deleteDirectory(int directoryId) {
        Connect();
        int id = 0;

        //region index values

        /*
        IN QueryId 			INT,
        IN vDirectoryId 	INT,
        IN DirectoryName	VARCHAR(1000),
        IN DirectorySize	INT,
        IN NumberOfFiles	INT,
        IN `Path`			VARCHAR(1000)
        * */

        //endregion

        try {
            CallableStatement cStmt = connection.prepareCall(
                    PreparedStatementHelper.callNameAndNumOfProcedures("usp_ExecuteDirectory", 6));

            cStmt.setInt(1, DELETE);
            cStmt.setInt(2, directoryId);
            cStmt.setString(3, "");
            cStmt.setInt(4, 0);
            cStmt.setInt(5, 0);
            cStmt.setString(6, "");

            ResultSet rs = cStmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }

            connection.close();

        } catch (SQLException sqlEx) {
            logger.error(sqlEx);
        }
        return id > 0;    }

    private static Directory HydrateObject(ResultSet rs) throws SQLException{
        /*
        a.DirectoryId,
        a.DirectoryName,
        a.DirectorySize,
        a.NumberOfFiles,
        a.Path
        */
        //notes:    HYDRATING AN OBJECT
        Directory directory = new Directory();

        directory.setDirectoryId(rs.getInt(1));
        directory.setDirectoryName(rs.getString(2));
        directory.setDirectorySize(rs.getInt(3));
        directory.setNumberOfFiles(rs.getInt(4));
        directory.setPath(rs.getString(5));

        return directory;

    }

    public void updateDirectorySizesAndFileCount(){
        Connect();

        try {
            connection.createStatement().execute("CALL usp_SetAndCountNumberOfFilesInDirectory();\n");
            connection.createStatement().execute("CALL usp_SetSizeOfDirectory();");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }



    //endregion CUSTOM METHODS


}
