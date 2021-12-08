package ua.com.alevel.fileoperators.impl;

import ua.com.alevel.csv.CSVHelper;
import ua.com.alevel.csv.impl.CSVHelperImpl;
import ua.com.alevel.fileoperators.FileDBOperator;
import ua.com.alevel.fileoperators.FileManager;
import ua.com.alevel.fileoperators.entity.FileEntity;
import ua.com.alevel.implementations.DoubleLinkedListImpl;
import ua.com.alevel.interfaces.DoubleLinkedList;
import ua.com.alevel.logic.entity.BaseEntity;

import java.io.*;
import java.lang.reflect.InvocationTargetException;

public class FileDBOperatorImpl<Entity extends BaseEntity> implements FileDBOperator<Entity> {

    private static int backuper = 0;
    private final Class<Entity> type;
    FileManager<Entity> fileManager = new FileManagerImpl<>();

    public FileDBOperatorImpl(Class<Entity> type) {
        this.type = type;
    }

    public Class<Entity> getMyType() {
        return this.type;
    }

    @Override
    public void backup() {
        try {
            fileManager.backup(getMyType());
        } catch (Exception e) {
            throw new RuntimeException("Cannot backup properly :" + e.getMessage());
        }
    }

    @Override
    public void writeToFile(DoubleLinkedList<Entity> list) {
        FileEntity<Entity> fileEntity = fileManager.check(getMyType());
        if (fileEntity == null) {
            try {
                fileEntity = fileManager.create(getMyType());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            assert fileEntity != null;
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileEntity.getFilePath() + fileEntity.getFileName()))) {
                CSVHelper<Entity> csv = new CSVHelperImpl<>(getMyType());
                writer.write(csv.getHeader());
                for (Entity entity : list) {
                    writer.write(csv.objectsToString(entity));
                }
            }
        } catch (Exception e) {
            //throw new RuntimeException("Cannot write to file :" + e.getMessage());
            e.printStackTrace();
        }
        if (backuper >= 5) {
            backuper = 0;
            backup();
        }
    }

    @Override
    public DoubleLinkedList<Entity> readFromFile() {
        DoubleLinkedList<Entity> list = new DoubleLinkedListImpl<>(getMyType());
        FileEntity<Entity> fileEntity = fileManager.check(getMyType());
        if (fileEntity == null) {
            return list;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(fileEntity.getFilePath() + fileEntity.getFileName()))) {
            String line;
            reader.readLine();
            CSVHelper<Entity> csv = new CSVHelperImpl<>(getMyType());
            while ((line = reader.readLine()) != null) {
                list.add(csv.stringToObject(csv.getHeader().trim().replace("\n", "").replace("\r", "").split(","), line));
            }
        } catch (IOException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        return list;
    }
}
