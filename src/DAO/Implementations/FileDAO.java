package DAO.Implementations;

import FO.File;

import java.util.List;

/**
 * Created by Carlos Zubiran on 5/13/2016.
 */
public interface FileDAO {

    //notes:    GET Methods

    public File getFileById(int fileId);
    public List<File> getFileList();

    //notes:    Execute Methods

    public int insertFile(File file);
    public boolean updateFile(File file);
    public boolean deleteFile(int fileId);

}
