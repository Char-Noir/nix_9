package ua.com.alevel.hw_10_web_repository.persistence.specification;

import org.springframework.data.jpa.domain.Specification;
import ua.com.alevel.hw_10_web_repository.persistence.datatable.DataTableRequest;
import ua.com.alevel.hw_10_web_repository.persistence.entity.BaseEntity;

public interface AbstractSpecification<E extends BaseEntity> {

    Specification<E> generateCriteriaPredicate(DataTableRequest request, Class<E> entityClass);
}