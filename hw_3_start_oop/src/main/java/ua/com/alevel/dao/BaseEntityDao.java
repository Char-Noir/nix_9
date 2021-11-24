package ua.com.alevel.dao;

import ua.com.alevel.logic.entity.BaseEntity;

public interface BaseEntityDao<Entity extends BaseEntity> {

    void create(Entity user);
    boolean update(Entity user);
    boolean delete(long id);
    Entity findById(long id);
    Entity[] findAll();
}
