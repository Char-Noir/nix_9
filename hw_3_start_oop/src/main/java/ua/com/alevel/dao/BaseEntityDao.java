package ua.com.alevel.dao;

import ua.com.alevel.entity.BaseEntity;
import ua.com.alevel.entity.User;

public interface BaseEntityDao<Entity extends BaseEntity> {

    void create(Entity user);
    boolean update(Entity user);
    boolean delete(long id);
    Entity findById(long id);
    Entity[] findAll();
}
