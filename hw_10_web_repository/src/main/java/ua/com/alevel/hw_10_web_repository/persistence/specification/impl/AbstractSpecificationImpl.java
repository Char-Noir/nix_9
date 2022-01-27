package ua.com.alevel.hw_10_web_repository.persistence.specification.impl;

import org.springframework.data.jpa.domain.Specification;
import ua.com.alevel.hw_10_web_repository.persistence.datatable.DataTableRequest;
import ua.com.alevel.hw_10_web_repository.persistence.entity.BaseEntity;
import ua.com.alevel.hw_10_web_repository.persistence.specification.AbstractSpecification;
import ua.com.alevel.hw_10_web_repository.util.SpecificationUtil;

import javax.persistence.criteria.Predicate;
import java.util.List;

public class AbstractSpecificationImpl<E extends BaseEntity> implements AbstractSpecification<E> {

    @Override
    public Specification<E> generateCriteriaPredicate(DataTableRequest request, Class<E> entityClass) {
        return (Specification<E>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = SpecificationUtil.generateSpecificationPredicates(request, entityClass, root, criteriaBuilder);
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}