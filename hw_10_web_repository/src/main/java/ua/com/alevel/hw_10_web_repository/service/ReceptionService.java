package ua.com.alevel.hw_10_web_repository.service;

import ua.com.alevel.hw_10_web_repository.persistence.datatable.DataTableRequest;
import ua.com.alevel.hw_10_web_repository.persistence.datatable.DataTableResponse;
import ua.com.alevel.hw_10_web_repository.persistence.entity.Reception;

public interface ReceptionService extends BaseService<Reception>{
    DataTableResponse<Reception> findAllByPatientId(DataTableRequest dataTableRequest, Long id);
    DataTableResponse<Reception> findAllByDoctorId(DataTableRequest dataTableRequest, Long id);
}
