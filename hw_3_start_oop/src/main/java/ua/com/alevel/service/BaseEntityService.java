package ua.com.alevel.service;

import ua.com.alevel.logic.entity.BaseEntity;

public interface BaseEntityService<Entity extends BaseEntity> {

     void create(Entity entity);
     boolean update(Entity entity);
     boolean delete(long id);
     Entity findById(long id);
     Entity[] findAll();
}
