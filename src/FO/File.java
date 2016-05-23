package FO;

/**
 * Created by Carlos Zubiran on 5/19/2016.
 */
public class File implements Comparable<File> {

    //region PROPERTIES

    private int FileId;
    private String FileName;
    private String FileType;
    private int FileSize;
    private String Path;
    private int FK_DirectoryId;

    //endregion PROPERTIES

    //region CONSTRUCTORS

    public File() {
        this.FileId = 0;
        this.FileName = "";
        this.FileSize = 0;
        this.FileSize = 0;
        this.Path = "";
        this.FK_DirectoryId = 0;
    }


    //endregion CONSTRUCTORS

    //region GETTERS / SETTERS

    public int getFileId() {
        return FileId;
    }

    public void setFileId(int fileId) {
        FileId = fileId;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
    }

    public String getFileType() {
        return FileType;
    }

    public void setFileType(String fileType) {
        FileType = fileType;
    }

    public int getFileSize() {
        return FileSize;
    }

    public void setFileSize(int fileSize) {
        FileSize = fileSize;
    }

    public String getPath() {
        return Path;
    }

    public void setPath(String path) {
        Path = path;
    }

    public int getFK_DirectoryId() {
        return FK_DirectoryId;
    }

    public void setFK_DirectoryId(int FK_DirectoryId) {
        this.FK_DirectoryId = FK_DirectoryId;
    }

//endregion GETTERS / SETTERS

    //region CUSTOM METHODS


    // This file compares the file sizes of two different files
    // used later with the collections class to enable a sort on a list
    @Override
    public int compareTo(File d) {
        if (this.getFileSize() < d.getFileSize()) {
            return -1;
        }
        else if (this.getFileSize() > d.getFileSize()) {
            return 1;
        }
        else {
            return 0;
        }
    }

    //endregion CUSTOM METHODS


}
