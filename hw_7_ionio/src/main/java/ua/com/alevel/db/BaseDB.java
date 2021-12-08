package ua.com.alevel.db;

import ua.com.alevel.logic.entity.BaseEntity;

public interface BaseDB<Entity extends BaseEntity> {
    void create(Entity entity);

    boolean update(Entity entity);

    boolean delete(Long id);

    boolean exist(Long id);

    Entity findById(Long id);

    Entity[] findAll();
}
