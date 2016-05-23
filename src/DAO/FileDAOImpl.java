package DAO;

import DAO.Implementations.FileDAO;
import FO.File;
import Helper.PreparedStatementHelper;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Carlos Zubiran on 5/19/2016.
 */
public class FileDAOImpl extends MySQL implements FileDAO {

    //region PROPERTIES


    //endregion PROPERTIES

    //region CONSTRUCTORS

    //endregion CONSTRUCTORS

    //region GETTERS / SETTERS


    //endregion GETTERS / SETTERS

    //region CUSTOM METHODS

    @Override
    public File getFileById(int fileId) {
        Connect();
        File file = null;   //not instantiated because if no records returned we don't want to use memory.

        try {
            CallableStatement cStmt = connection.prepareCall(
                    PreparedStatementHelper.callNameAndNumOfProcedures("usp_GetFile", 6));

            cStmt.setInt(1, GET_BY_ID);
            cStmt.setInt(2, fileId);
            ResultSet rs = cStmt.executeQuery();

            if (rs.next()) {
                file = HydrateObject(rs);
            }

            connection.close();

        } catch (SQLException ex) {
            logger.error(ex);
        }

        return file;

    }

    @Override
    public List<File> getFileList() {
        Connect();
        List<File> fileList = new ArrayList<File>();
        try {
            CallableStatement cStmt = connection.prepareCall(
                    PreparedStatementHelper.callNameAndNumOfProcedures("usp_GetFile", 2));

            cStmt.setInt(1, GET_COLLECTION);
            cStmt.setInt(2,0);
            ResultSet rs = cStmt.executeQuery();

            while(rs.next()) {

                fileList.add(HydrateObject(rs));
            }

            connection.close();



        } catch (SQLException ex) {
            logger.error(ex);
        }

        return fileList;

    }

    @Override
    public int insertFile(File file) {

        Connect();
        int id = 0;

        //region index values

        /*
        IN QueryId 			INT,
        IN vFileId 			INT,
        IN FileName			VARCHAR(1000),
        IN FileType			VARCHAR(1000),
        IN FileSize			INT,
        IN `Path`			VARCHAR(1000),
        IN vFK_DirectoryId	INT
        * */

        //endregion

        try {
            CallableStatement cStmt = connection.prepareCall(
                    PreparedStatementHelper.callNameAndNumOfProcedures("usp_ExecuteFile", 7));

            cStmt.setInt(1, INSERT);
            cStmt.setInt(2, 0);
            cStmt.setString(3, file.getFileName());
            cStmt.setString(4, file.getFileType());
            cStmt.setInt(5, file.getFileSize());
            cStmt.setString(6, file.getPath());
            cStmt.setInt(7, file.getFK_DirectoryId());

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
    public boolean updateFile(File file) {
        Connect();
        int id = 0;

        //region index values

        /*
        IN QueryId 			INT,
        IN vFileId 			INT,
        IN FileName			VARCHAR(1000),
        IN FileType			VARCHAR(1000),
        IN FileSize			INT,
        IN `Path`			VARCHAR(1000),
        IN vFK_DirectoryId	INT
        * */

        //endregion

        try {
            CallableStatement cStmt = connection.prepareCall(
                    PreparedStatementHelper.callNameAndNumOfProcedures("usp_ExecuteFile", 7));

            cStmt.setInt(1, UPDATE);                            // Changed from insert method
            cStmt.setInt(2, file.getFileId());              // Changed from insert method
            cStmt.setString(3, file.getFileName());
            cStmt.setString(4, file.getFileType());
            cStmt.setInt(5, file.getFileSize());
            cStmt.setString(6, file.getPath());
            cStmt.setInt(7, file.getFK_DirectoryId());

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
    public boolean deleteFile(int fileId) {
        Connect();
        int id = 0;

        //region index values

        /*
        IN QueryId 			INT,
        IN vFileId 			INT,
        IN FileName			VARCHAR(1000),
        IN FileType			VARCHAR(1000),
        IN FileSize			INT,
        IN `Path`			VARCHAR(1000),
        IN vFK_DirectoryId	INT
        * */

        //endregion

        try {
            CallableStatement cStmt = connection.prepareCall(
                    PreparedStatementHelper.callNameAndNumOfProcedures("usp_ExecuteFile", 7));

            cStmt.setInt(1, DELETE);
            cStmt.setInt(2, fileId);
            cStmt.setString(3, "");
            cStmt.setString(4, "");
            cStmt.setInt(5, 0);
            cStmt.setString(6, "");
            cStmt.setInt(7, 0);

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

    private static File HydrateObject(ResultSet rs) throws SQLException{
        /*
        a.FileId,
        a.FileName,
        a.FileType,
        a.FileSize,
        a.`Path`,
        a.FK_DirectoryId
        */
        //notes:    HYDRATING AN OBJECT
        File file = new File();


        file.setFileId(rs.getInt(1));
        file.setFileName(rs.getString(2));
        file.setFileType(rs.getString(3));
        file.setFileSize(rs.getInt(4));
        file.setPath(rs.getString(5));
        file.setFK_DirectoryId(rs.getInt(6));

        return file;

    }



    //endregion CUSTOM METHODS


}
