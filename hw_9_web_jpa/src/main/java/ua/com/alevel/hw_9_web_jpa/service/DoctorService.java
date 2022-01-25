package ua.com.alevel.hw_9_web_jpa.service;

import ua.com.alevel.hw_9_web_jpa.persistence.entity.Doctor;

public interface DoctorService extends BaseService<Doctor> {
    Doctor findDoctorByPatientId(Long id);

}
