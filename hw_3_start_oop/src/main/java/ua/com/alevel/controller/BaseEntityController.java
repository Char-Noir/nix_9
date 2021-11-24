package ua.com.alevel.controller;

import ua.com.alevel.console.responce.Responce;
import ua.com.alevel.logic.entity.BaseEntity;

public interface BaseEntityController<Entity extends BaseEntity> {
    Responce create(int age, String name);
    Responce update(long id, int age, String name);
    Responce delete(long id);
    Responce findById(long id);
    Responce findAll();
}
