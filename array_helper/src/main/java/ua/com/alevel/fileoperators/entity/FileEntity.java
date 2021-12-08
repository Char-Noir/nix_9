package ua.com.alevel.fileoperators.entity;

import ua.com.alevel.logic.entity.BaseEntity;

public class FileEntity<Entity extends BaseEntity> {

    String fileName;
    String filePath;
    Class<Entity> clazz;

    public FileEntity(String fileName, String filePath, Class<Entity> clazz) {
        this.fileName = fileName+".csv";
        this.filePath = filePath;
        this.clazz = clazz;
    }

    public String getFileName() {
        return fileName;
    }

    public FileEntity<Entity> setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public String getFilePath() {
        return filePath;
    }

    public FileEntity<Entity> setFilePath(String filePath) {
        this.filePath = filePath;
        return this;
    }

    public Class<Entity> getClazz() {
        return clazz;
    }

    public FileEntity<Entity> setClazz(Class<Entity> clazz) {
        this.clazz = clazz;
        return this;
    }
}
