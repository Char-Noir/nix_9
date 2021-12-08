package ua.com.alevel.fileoperators;

import ua.com.alevel.fileoperators.entity.FileEntity;
import ua.com.alevel.logic.entity.BaseEntity;

import java.io.IOException;

public interface FileManager<Entity extends BaseEntity> {
    FileEntity<Entity> create(Class<Entity> clazz) throws IOException;
    void delete(Class<? extends BaseEntity> clazz);
    FileEntity<Entity> check(Class<Entity> clazz);
    void backup(Class<Entity> clazz) throws IOException;
}
