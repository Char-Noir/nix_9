package ua.com.alevel.service;

import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.doctor.Doctor;
import ua.com.alevel.service.BaseWorkerService;

public interface DoctorService extends BaseWorkerService<Doctor> {
    DataTableResponse<Doctor> findNotDeleted(DataTableRequest dataTableRequest);
}
