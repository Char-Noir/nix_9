package ua.com.alevel.hw_8_web_jdbc.persistence.dao;

import ua.com.alevel.hw_8_web_jdbc.persistence.entity.Doctor;

public interface DoctorDao extends BaseDao<Doctor> {
     Doctor findDoctorByPatientId(Long id);
}
