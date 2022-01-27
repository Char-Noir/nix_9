package ua.com.alevel.hw_10_web_repository.persistence.crud;


import ua.com.alevel.hw_10_web_repository.persistence.datatable.DataTableRequest;
import ua.com.alevel.hw_10_web_repository.persistence.datatable.DataTableResponse;
import ua.com.alevel.hw_10_web_repository.persistence.entity.BaseEntity;
import ua.com.alevel.hw_10_web_repository.persistence.repository.AbstractRepository;

import java.util.Optional;

public interface CrudRepositoryHelper<E extends BaseEntity, R extends AbstractRepository<E>> {

    void create(R repository, E entity);

    void update(R repository, E entity);

    void delete(R repository, Long id);

    Optional<E> findById(R repository, Long id);

    DataTableResponse<E> findAll(R repository, DataTableRequest dataTableRequest, Class<E> entityClass);

    void checkExist(R repository, Long id);
}