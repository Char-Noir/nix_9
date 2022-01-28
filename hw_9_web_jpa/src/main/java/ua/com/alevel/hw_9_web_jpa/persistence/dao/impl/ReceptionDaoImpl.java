package ua.com.alevel.hw_9_web_jpa.persistence.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.hw_9_web_jpa.exception.PageNotExistException;
import ua.com.alevel.hw_9_web_jpa.persistence.dao.ReceptionDao;
import ua.com.alevel.hw_9_web_jpa.persistence.datatable.DataTableRequest;
import ua.com.alevel.hw_9_web_jpa.persistence.datatable.DataTableResponse;
import ua.com.alevel.hw_9_web_jpa.persistence.entity.Doctor;
import ua.com.alevel.hw_9_web_jpa.persistence.entity.Patient;
import ua.com.alevel.hw_9_web_jpa.persistence.entity.Reception;
import ua.com.alevel.hw_9_web_jpa.persistence.entity.Reception_;

import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Metamodel;
import java.util.List;


@Repository
@Transactional
public class ReceptionDaoImpl implements ReceptionDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void create(Reception entity) {
        entityManager.persist(entity);
    }


    @Override
    public void update(Reception entity) {
        entityManager.merge(entity);
    }

    @Override
    public void delete(Long id) {
        int isSuccessful = entityManager.createQuery("delete from Reception where idReception = :id")
                .setParameter("id", id)
                .executeUpdate();
        if (isSuccessful == 0) {
            throw new OptimisticLockException("doctors modified concurrently");
        }
    }

    @Override
    public boolean existById(Long id) {
        Query query = entityManager.createQuery("select count(r.idReception) from Reception r where r.idReception = :id")
                .setParameter("id", id);
        return (Long) query.getSingleResult() == 1;
    }

    @Override
    public Reception findById(Long id) {
        return entityManager.find(Reception.class, id);
    }


    @Override
    public DataTableResponse<Reception> findAll(DataTableRequest request) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Reception> criteriaQuery = criteriaBuilder.createQuery(Reception.class);
        Root<Reception> from = criteriaQuery.from(Reception.class);
        if (request.getOrder().equals("desc")) {
            criteriaQuery.orderBy(criteriaBuilder.desc(from.get(request.getSort())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.asc(from.get(request.getSort())));
        }

        int page = (request.getCurrentPage() - 1) * request.getPageSize();
        int size = page + request.getPageSize();

        List<Reception> items = entityManager.createQuery(criteriaQuery)
                .setFirstResult(page)
                .setMaxResults(size)
                .getResultList();
        if (items.isEmpty() && request.getCurrentPage() > 1) {
            throw new PageNotExistException("Page not found!");
        }

        DataTableResponse<Reception> response = new DataTableResponse<>();
        response.setSort(request.getSort());
        response.setOrder(request.getOrder());
        response.setCurrentPage(request.getCurrentPage());
        response.setPageSize(request.getPageSize());
        response.setItems(items);

        return response;
    }

    @Override
    public long count() {
        Query query = entityManager.createQuery("select count(r.idReception) from Reception r");
        return (Long) query.getSingleResult();
    }

    @Override
    public DataTableResponse<Reception> findAllByPatientId(DataTableRequest request, Long id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Reception> criteriaQuery = criteriaBuilder.createQuery(Reception.class);
        Metamodel m = entityManager.getMetamodel();
        //EntityType<Reception> Patient_ = m.entity(Reception.class);

        Root<Reception> from = criteriaQuery.from(Reception.class);
        Join<Reception, Patient> owner = from.join(Reception_.patient);

        if (request.getOrder().equals("desc")) {
            criteriaQuery.orderBy(criteriaBuilder.desc(from.get(request.getSort())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.asc(from.get(request.getSort())));
        }
        criteriaQuery.where(criteriaBuilder.equal(owner.get("idPatient"), id));

        int page = (request.getCurrentPage() - 1) * request.getPageSize();
        int size = page + request.getPageSize();

        List<Reception> items = entityManager.createQuery(criteriaQuery)
                .setFirstResult(page)
                .setMaxResults((size<1)? (int) count() :size)
                .getResultList();

        if (items.isEmpty() && request.getCurrentPage() > 1) {
            throw new PageNotExistException("Page not found!");
        }

        DataTableResponse<Reception> response = new DataTableResponse<>();
        response.setSort(request.getSort());
        response.setOrder(request.getOrder());
        response.setCurrentPage(request.getCurrentPage());
        response.setPageSize(request.getPageSize());
        response.setItems(items);

        return response;
    }


    @Override
    public long countByPatient(Long id) {
        Query query = entityManager.createQuery("select count(r.idReception) from Reception r where r.patient.idPatient=:id").setParameter("id", id);
        return (Long) query.getSingleResult();
    }

    @Override
    public DataTableResponse<Reception> findAllByDoctorId(DataTableRequest request, Long id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Reception> criteriaQuery = criteriaBuilder.createQuery(Reception.class);
        Metamodel m = entityManager.getMetamodel();
        //EntityType<Reception> Patient_ = m.entity(Reception.class);

        Root<Reception> from = criteriaQuery.from(Reception.class);
        Join<Reception, Doctor> owner = from.join(Reception_.doctor);

        if (request.getOrder().equals("desc")) {
            criteriaQuery.orderBy(criteriaBuilder.desc(from.get(request.getSort())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.asc(from.get(request.getSort())));
        }
        criteriaQuery.where(criteriaBuilder.equal(owner.get("idDoctor"), id));

        int page = (request.getCurrentPage() - 1) * request.getPageSize();
        int size = page + request.getPageSize();

        List<Reception> items = entityManager.createQuery(criteriaQuery)
                .setFirstResult(page)
                .setMaxResults(size)
                .getResultList();

        if (items.isEmpty() && request.getCurrentPage() > 1) {
            throw new PageNotExistException("Page not found!");
        }

        DataTableResponse<Reception> response = new DataTableResponse<>();
        response.setSort(request.getSort());
        response.setOrder(request.getOrder());
        response.setCurrentPage(request.getCurrentPage());
        response.setPageSize(request.getPageSize());
        response.setItems(items);

        return response;
    }


    @Override
    public long countByDoctor(Long id) {
        Query query = entityManager.createQuery("select count(r.idReception) from Reception r where r.doctor.idDoctor=:id").setParameter("id", id);
        return (Long) query.getSingleResult();
    }
}
