package DAO.Implementations;

import FO.Directory;

import java.util.List;

/**
 * Created by Carlos Zubiran on 5/13/2016.
 */
public interface DirectoryDAO {

    //notes:    GET Methods

    public Directory getDirectoryById(int directoryId);
    public List<Directory> getDirectoryList();

    //notes:    Execute Methods

    public int insertDirectory(Directory directory);
    public boolean updateDirectory(Directory directory);
    public boolean deleteDirectory(int directoryId);
    public void updateDirectorySizesAndFileCount();

    }
