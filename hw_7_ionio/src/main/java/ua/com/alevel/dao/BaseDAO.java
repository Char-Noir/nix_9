package ua.com.alevel.dao;

import ua.com.alevel.logic.entity.BaseEntity;

public interface BaseDAO<Entity extends BaseEntity> {

    void create(Entity entity);

    boolean update(Entity entity);

    boolean delete(Long id);

    Entity findById(Long id);

    Entity[] findAll();
}