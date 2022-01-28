package ua.com.alevel.hw_9_web_jpa.persistence.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.hw_9_web_jpa.exception.PageNotExistException;
import ua.com.alevel.hw_9_web_jpa.persistence.dao.DoctorDao;
import ua.com.alevel.hw_9_web_jpa.persistence.datatable.DataTableRequest;
import ua.com.alevel.hw_9_web_jpa.persistence.datatable.DataTableResponse;
import ua.com.alevel.hw_9_web_jpa.persistence.entity.Doctor;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class DoctorDaoImpl implements DoctorDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Doctor entity) {
        entityManager.persist(entity);
    }

    @Override
    public void update(Doctor entity) {
        entityManager.merge(entity);
    }

    @Override
    public void delete(Long id) {
        int isSuccessful = entityManager.createQuery("delete from Doctor where idDoctor = :id")
                .setParameter("id", id)
                .executeUpdate();
        if (isSuccessful == 0) {
            throw new OptimisticLockException("doctors modified concurrently");
        }
    }

    @Override
    public boolean existById(Long id) {
        Query query = entityManager.createQuery("select count(d.idDoctor) from Doctor d where d.idDoctor = :id")
                .setParameter("id", id);
        return (Long) query.getSingleResult() == 1;
    }

    @Override
    public Doctor findById(Long id) {
        return entityManager.find(Doctor.class, id);
    }

    @Override
    public DataTableResponse<Doctor> findAll(DataTableRequest request) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Doctor> criteriaQuery = criteriaBuilder.createQuery(Doctor.class);
        Root<Doctor> from = criteriaQuery.from(Doctor.class);
        if (request.getOrder().equals("desc")) {
            criteriaQuery.orderBy(criteriaBuilder.desc(from.get(request.getSort())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.asc(from.get(request.getSort())));
        }

        int page = (request.getCurrentPage() - 1) * request.getPageSize();
        int size = page + request.getPageSize();

        List<Doctor> items = entityManager.createQuery(criteriaQuery)
                .setFirstResult(page)
                .setMaxResults((size<1)? (int) count() :size)
                .getResultList();
        if (items.isEmpty() && request.getCurrentPage() > 1) {
            throw new PageNotExistException("Page not found!");
        }
        Map<Object, Object> otherParamMap = new HashMap<>();
        for (Doctor doctor : items) {
            otherParamMap.put(doctor.getIdDoctor(), doctor.getReceptions().size());
        }
        DataTableResponse<Doctor> response = new DataTableResponse<>();
        response.setSort(request.getSort());
        response.setOrder(request.getOrder());
        response.setCurrentPage(request.getCurrentPage());
        response.setPageSize(request.getPageSize());
        response.setItems(items);
        response.setOtherParamMap(otherParamMap);

        return response;
    }

    @Override
    public long count() {
        Query query = entityManager.createQuery("select count(d.idDoctor) from Doctor d");
        return (Long) query.getSingleResult();
    }


    @Override
    public Doctor findDoctorByPatientId(Long id) {
        TypedQuery<Doctor> query
                = entityManager.createQuery(
                "SELECT dp.doctor FROM DoctorPatient dp where dp.patient.idPatient = :id order by dp.createDate desc", Doctor.class).setParameter("id", id).setMaxResults(1);
        return query.getSingleResult();
    }

    @Override
    public Long countDoctorByPatientId(Long id) {
        Query query = entityManager.createQuery("SELECT count(dp.doctor.idDoctor) FROM DoctorPatient dp where dp.patient.idPatient = :id").setParameter("id", id);
        return (Long) query.getSingleResult();
    }


}
