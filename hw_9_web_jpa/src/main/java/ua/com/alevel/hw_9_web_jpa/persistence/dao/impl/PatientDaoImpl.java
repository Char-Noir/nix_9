package ua.com.alevel.hw_9_web_jpa.persistence.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.hw_9_web_jpa.exception.PageNotExistException;
import ua.com.alevel.hw_9_web_jpa.persistence.dao.PatientDao;
import ua.com.alevel.hw_9_web_jpa.persistence.datatable.DataTableRequest;
import ua.com.alevel.hw_9_web_jpa.persistence.datatable.DataTableResponse;
import ua.com.alevel.hw_9_web_jpa.persistence.entity.Patient;

import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@Transactional
public class PatientDaoImpl implements PatientDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void create(Patient entity) {
        entityManager.persist(entity);
    }

    @Override
    public void update(Patient entity) {
        entityManager.merge(entity);
    }


    @Override
    public void delete(Long id) {
        int isSuccessful = entityManager.createQuery("delete from Patient where idPatient = :id")
                .setParameter("id", id)
                .executeUpdate();
        if (isSuccessful == 0) {
            throw new OptimisticLockException("patients modified concurrently");
        }
    }

    @Override
    public boolean existById(Long id) {
        Query query = entityManager.createQuery("select count(p.idPatient) from Patient p where p.idPatient = :id")
                .setParameter("id", id);
        return (Long) query.getSingleResult() == 1;
    }

    @Override
    public Patient findById(Long id) {
        return entityManager.find(Patient.class, id);
    }

    @Override
    public DataTableResponse<Patient> findAll(DataTableRequest request) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Patient> criteriaQuery = criteriaBuilder.createQuery(Patient.class);
        Root<Patient> from = criteriaQuery.from(Patient.class);
        if (request.getOrder().equals("desc")) {
            criteriaQuery.orderBy(criteriaBuilder.desc(from.get(request.getSort())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.asc(from.get(request.getSort())));
        }

        int page = (request.getCurrentPage() - 1) * request.getPageSize();
        int size = page + request.getPageSize();

        List<Patient> items = entityManager.createQuery(criteriaQuery)
                .setFirstResult(page)
                .setMaxResults((size<1)? (int) count() :size)
                .getResultList();
        if (items.isEmpty() && request.getCurrentPage() > 1) {
            throw new PageNotExistException("Page not found!");
        }
        DataTableResponse<Patient> response = new DataTableResponse<>();
        response.setSort(request.getSort());
        response.setOrder(request.getOrder());
        response.setCurrentPage(request.getCurrentPage());
        response.setPageSize(request.getPageSize());
        response.setItems(items);

        return response;
    }

    @Override
    public long count() {
        Query query = entityManager.createQuery("select count(p.idPatient) from Patient p");
        return (Long) query.getSingleResult();
    }

}
