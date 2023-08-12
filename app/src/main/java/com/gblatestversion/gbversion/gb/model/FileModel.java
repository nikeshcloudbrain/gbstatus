package com.gblatestversion.gbversion.gb.model;

import java.io.Serializable;

public class FileModel implements Serializable {

    public String fileName;
    public String filePath;
    public boolean isSelected = false;
    public String fileSize;

    public FileModel(String fileName, String filePath, boolean isSelected, String fileSize) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.isSelected = isSelected;
        this.fileSize = fileSize;
    }
}

