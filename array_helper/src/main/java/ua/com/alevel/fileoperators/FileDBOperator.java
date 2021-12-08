package ua.com.alevel.fileoperators;

import ua.com.alevel.interfaces.DoubleLinkedList;
import ua.com.alevel.logic.entity.BaseEntity;

public interface FileDBOperator<Entity extends BaseEntity> {

    void backup();
    void writeToFile(DoubleLinkedList<Entity> list);
    DoubleLinkedList<Entity> readFromFile();
}
