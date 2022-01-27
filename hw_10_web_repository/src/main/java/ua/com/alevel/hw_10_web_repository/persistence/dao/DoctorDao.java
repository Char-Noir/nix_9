package ua.com.alevel.hw_10_web_repository.persistence.dao;

import ua.com.alevel.hw_10_web_repository.persistence.entity.Doctor;

public interface DoctorDao extends BaseDao<Doctor> {
     Doctor findDoctorByPatientId(Long id);
}
