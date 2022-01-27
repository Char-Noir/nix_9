package ua.com.alevel.hw_10_web_repository.persistence.crud.impl;

import org.apache.commons.collections4.MapUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ua.com.alevel.hw_10_web_repository.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.hw_10_web_repository.persistence.datatable.DataTableRequest;
import ua.com.alevel.hw_10_web_repository.persistence.datatable.DataTableResponse;
import ua.com.alevel.hw_10_web_repository.persistence.entity.BaseEntity;
import ua.com.alevel.hw_10_web_repository.persistence.repository.AbstractRepository;
import ua.com.alevel.hw_10_web_repository.persistence.specification.AbstractSpecification;
import ua.com.alevel.hw_10_web_repository.persistence.specification.impl.AbstractSpecificationImpl;

import java.util.Optional;

@Service
public class CrudRepositoryHelperImpl<
        E extends BaseEntity,
        R extends AbstractRepository<E>>
        implements CrudRepositoryHelper<E, R> {

    @Override
    public void create(R repository, E entity) {
        System.out.println(entity);
        repository.save(entity);
    }

    @Override
    public void update(R repository, E entity) {
        checkExist(repository, entity.getId());
        repository.save(entity);
    }

    @Override
    public void delete(R repository, Long id) {
        checkExist(repository, id);
        repository.deleteById(id);
    }

    @Override
    public Optional<E> findById(R repository, Long id) {
        return repository.findById(id);
    }

    @Override
    public DataTableResponse<E> findAll(R repository, DataTableRequest dataTableRequest, Class<E> entityClass) {
        int page = dataTableRequest.getCurrentPage() - 1;
        int size = dataTableRequest.getPageSize();
        String sortParam = dataTableRequest.getSort();
        String orderParam = dataTableRequest.getOrder();

        Sort sort = orderParam.equals("desc")
                ? Sort.by(sortParam).descending()
                : Sort.by(sortParam).ascending();

        Specification<E> specification = null;
        if (MapUtils.isNotEmpty(dataTableRequest.getRequestParamMap())) {
            AbstractSpecification<E> eAbstractSpecification = new AbstractSpecificationImpl<>();
            specification = eAbstractSpecification.generateCriteriaPredicate(dataTableRequest, entityClass);
        }

        PageRequest request = PageRequest.of(page, size, sort);

        Page<E> pageEntity;
        if (specification == null) {
            pageEntity = repository.findAll(request);
        } else {
            pageEntity = repository.findAll(specification, request);
        }

        DataTableResponse<E> dataTableResponse = new DataTableResponse<>();
        dataTableResponse.setSort(sortParam);
        dataTableResponse.setOrder(orderParam);
        dataTableResponse.setPageSize(size);
        dataTableResponse.setCurrentPage(page);
        dataTableResponse.setItemsSize(pageEntity.getTotalElements());
        dataTableResponse.setItemsSize(pageEntity.getTotalPages());
        dataTableResponse.setItems(pageEntity.getContent());

        return dataTableResponse;
    }

    @Override
    public void checkExist(R repository, Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("entity not found");
        }
    }
}