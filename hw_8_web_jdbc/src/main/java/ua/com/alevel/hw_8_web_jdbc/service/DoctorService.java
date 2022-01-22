package ua.com.alevel.hw_8_web_jdbc.service;

import ua.com.alevel.hw_8_web_jdbc.persistence.entity.Doctor;

public interface DoctorService extends BaseService<Doctor> {
    Doctor findDoctorByPatientId(Long id);
}
