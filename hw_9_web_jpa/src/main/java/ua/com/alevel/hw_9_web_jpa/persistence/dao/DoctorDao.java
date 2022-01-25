package ua.com.alevel.hw_9_web_jpa.persistence.dao;

import ua.com.alevel.hw_9_web_jpa.persistence.entity.Doctor;

public interface DoctorDao extends BaseDao<Doctor> {
     Doctor findDoctorByPatientId(Long id);
     Long countDoctorByPatientId(Long id);
}
