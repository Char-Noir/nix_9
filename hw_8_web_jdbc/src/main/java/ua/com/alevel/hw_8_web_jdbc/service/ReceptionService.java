package ua.com.alevel.hw_8_web_jdbc.service;

import ua.com.alevel.hw_8_web_jdbc.persistence.datatable.DataTableRequest;
import ua.com.alevel.hw_8_web_jdbc.persistence.datatable.DataTableResponse;
import ua.com.alevel.hw_8_web_jdbc.persistence.entity.Patient;
import ua.com.alevel.hw_8_web_jdbc.persistence.entity.Reception;

public interface ReceptionService extends BaseService<Reception>{
    DataTableResponse<Reception> findAllByPatientId(DataTableRequest dataTableRequest, Long id);
    DataTableResponse<Reception> findAllByDoctorId(DataTableRequest dataTableRequest, Long id);
}
