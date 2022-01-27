package ua.com.alevel.hw_10_web_repository.service;

import ua.com.alevel.hw_10_web_repository.persistence.entity.Doctor;

public interface DoctorService extends BaseService<Doctor> {
    Doctor findDoctorByPatientId(Long id);
}
