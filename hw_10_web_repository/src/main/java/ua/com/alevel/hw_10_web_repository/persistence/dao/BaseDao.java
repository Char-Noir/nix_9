package ua.com.alevel.hw_10_web_repository.persistence.dao;

import ua.com.alevel.hw_10_web_repository.persistence.datatable.DataTableRequest;
import ua.com.alevel.hw_10_web_repository.persistence.datatable.DataTableResponse;
import ua.com.alevel.hw_10_web_repository.persistence.entity.BaseEntity;

import java.util.Optional;


public interface BaseDao<ENTITY extends BaseEntity> {

    void create(ENTITY entity);

    void update(ENTITY entity);

    void delete(Long id);

    Optional<ENTITY> findById(Long id);

    DataTableResponse<ENTITY> findAll(DataTableRequest request);

}