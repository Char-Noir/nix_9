package ua.com.alevel.hw_9_web_jpa.service;

import ua.com.alevel.hw_9_web_jpa.persistence.datatable.DataTableRequest;
import ua.com.alevel.hw_9_web_jpa.persistence.datatable.DataTableResponse;
import ua.com.alevel.hw_9_web_jpa.persistence.entity.Reception;

public interface ReceptionService extends BaseService<Reception>{
    DataTableResponse<Reception> findAllByPatientId(DataTableRequest dataTableRequest, Long id);
    DataTableResponse<Reception> findAllByDoctorId(DataTableRequest dataTableRequest, Long id);
}
