package ua.com.alevel.service;

import ua.com.alevel.logic.entity.BaseEntity;

public interface BaseService<ENTITY extends BaseEntity> {

    void create(ENTITY entity);

    boolean update(ENTITY entity);

    boolean delete(Long id);

    ENTITY findById(Long id);

    ENTITY[] findAll();
}