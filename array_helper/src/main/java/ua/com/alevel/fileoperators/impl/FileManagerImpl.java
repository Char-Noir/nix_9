package ua.com.alevel.fileoperators.impl;

import ua.com.alevel.fileoperators.FileManager;
import ua.com.alevel.fileoperators.entity.FileEntity;
import ua.com.alevel.logic.entity.BaseEntity;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

public class FileManagerImpl<Entity extends BaseEntity> implements FileManager<Entity> {

    private static final String outerPath = System.getProperty("user.dir");

    @Override
    public FileEntity<Entity> create(Class<Entity> clazz) throws IOException {
        FileEntity<Entity> fileEntity = new FileEntity<>(clazz.getSimpleName(), outerPath, clazz);
        Path newFilePath = Paths.get(fileEntity.getFilePath() + '\\' + fileEntity.getFileName());
        Files.createFile(newFilePath);
        return fileEntity;
    }

    @Override
    public void delete(Class<? extends BaseEntity> clazz) {
        FileEntity<?> fileEntity = new FileEntity<>(clazz.getSimpleName(), outerPath, clazz);
        File targetFile = new File(fileEntity.getFilePath() + '\\' + fileEntity.getFileName());
        targetFile.delete();
    }

    @Override
    public FileEntity<Entity> check(Class<Entity> clazz) {
        FileEntity<Entity> fileEntity = new FileEntity<>(clazz.getSimpleName(), outerPath, clazz);
        File targetFile = new File(fileEntity.getFilePath() + fileEntity.getFileName());
        return (targetFile.exists() ? fileEntity : null);
    }

    @Override
    public void backup(Class<Entity> clazz) throws IOException {
        if (check(clazz) == null) {
            return;
        }
        FileEntity<Entity> src = new FileEntity<>(clazz.getSimpleName(), outerPath, clazz);
        FileEntity<Entity> dest = new FileEntity<>(clazz.getSimpleName() + ':' + LocalDate.now(), outerPath, clazz);
        copyFile(src, dest);
    }

    private void copyFile(FileEntity<Entity> src, FileEntity<Entity> dest) throws IOException {
        Path fileToMovePath = Paths.get(src.getFilePath() + src.getFileName());
        Path targetPath = Paths.get(dest.getFilePath() + dest.getFileName());
        Files.move(fileToMovePath, targetPath);

    }

}
