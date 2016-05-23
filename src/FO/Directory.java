package FO;

/**
 * Created by Carlos Zubiran on 5/19/2016.
 */
public class Directory {

    //region PROPERTIES

    private int DirectoryId;
    private String DirectoryName;
    private int DirectorySize;
    private int NumberOfFiles;
    private String Path;

    //endregion PROPERTIES

    //region CONSTRUCTORS

    //endregion CONSTRUCTORS

    //region GETTERS / SETTERS

    public int getDirectoryId() {
        return DirectoryId;
    }

    public void setDirectoryId(int directoryId) {
        DirectoryId = directoryId;
    }

    public String getDirectoryName() {
        return DirectoryName;
    }

    public void setDirectoryName(String directoryName) {
        DirectoryName = directoryName;
    }

    public int getDirectorySize() {
        return DirectorySize;
    }

    public void setDirectorySize(int directorySize) {
        DirectorySize = directorySize;
    }

    public int getNumberOfFiles() {
        return NumberOfFiles;
    }

    public void setNumberOfFiles(int numberOfFiles) {
        NumberOfFiles = numberOfFiles;
    }

    public String getPath() {
        return Path;
    }

    public void setPath(String path) {
        Path = path;
    }

    //endregion GETTERS / SETTERS

    //region CUSTOM METHODS

    //endregion CUSTOM METHODS


}
