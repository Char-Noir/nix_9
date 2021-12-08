package ua.com.alevel.controller;

import ua.com.alevel.console.responce.Responce;
import ua.com.alevel.logic.entity.BaseEntity;

public interface BaseController<Entity extends BaseEntity> {
    void initDB();
}
