package ua.com.alevel.module3.persistense.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.module3.persistense.dao.OperationDao;
import ua.com.alevel.module3.persistense.datatable.DataTableRequest;
import ua.com.alevel.module3.persistense.datatable.DataTableResponse;
import ua.com.alevel.module3.persistense.entity.Account;
import ua.com.alevel.module3.persistense.entity.Operation;
import ua.com.alevel.module3.persistense.entity.Operation_;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Metamodel;
import java.util.List;

@Transactional
@Repository
public class OperationDaoImpl implements OperationDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean existById(Long id) {
        return false;
    }

    @Override
    public Operation findById(Long id) {
        return null;
    }

    @Override
    public DataTableResponse<Operation> findAll(DataTableRequest request) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void create(Operation entity) {
        entityManager.persist(entity);
    }

    @Override
    public void update(Operation entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public DataTableResponse<Operation> findAllByAccount(DataTableRequest request, Long id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Operation> criteriaQuery = criteriaBuilder.createQuery(Operation.class);
        Metamodel m = entityManager.getMetamodel();

        Root<Operation> from = criteriaQuery.from(Operation.class);
        Join<Operation, Account> owner = from.join(Operation_.in);
        Join<Operation, Account> owner2 = from.join(Operation_.out);

        if (request.getOrder().equals("desc")) {
            criteriaQuery.orderBy(criteriaBuilder.desc(from.get(request.getSort())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.asc(from.get(request.getSort())));
        }
        criteriaQuery.where(criteriaBuilder.or(criteriaBuilder.equal(owner.get("id"), id), criteriaBuilder.equal(owner2.get("id"), id)));

        int page = (request.getCurrentPage() - 1) * request.getPageSize();
        int size = page + request.getPageSize();

        List<Operation> items = entityManager.createQuery(criteriaQuery)
                .setFirstResult(page)
                .setMaxResults((size<1)? (int) countByAccount(id) :size)
                .getResultList();


        DataTableResponse<Operation> response = new DataTableResponse<>();
        response.setSort(request.getSort());
        response.setOrder(request.getOrder());
        response.setCurrentPage(request.getCurrentPage());
        response.setPageSize(request.getPageSize());
        response.setItems(items);

        return response;
    }

    @Override
    public long countByAccount(Long id) {
        Query query = entityManager.createQuery("select count(p.id) from Operation p where p.in.id = :id or p.out.id=:id")
                .setParameter("id", id);
        return (Long) query.getSingleResult();
    }
}
