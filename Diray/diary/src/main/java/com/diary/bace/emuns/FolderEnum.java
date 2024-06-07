package com.diary.bace.emuns;


public enum FolderEnum {
    PICTURE_FOLDER(1, "picture","/images/"),
    FILE_FOLDER(2, "file","/files/"),
    ;
    private Integer code;
    private String folderName;
    private String mappingUrl;

    FolderEnum(Integer code, String folderName, String mappingUrl) {
        this.code = code;
        this.folderName = folderName;
        this.mappingUrl = mappingUrl;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public String getMappingUrl() {
        return mappingUrl;
    }

    public void setMappingUrl(String mappingUrl) {
        this.mappingUrl = mappingUrl;
    }

}