package ua.com.alevel.hw_8_web_jdbc.persistence.dao;

import ua.com.alevel.hw_8_web_jdbc.persistence.datatable.DataTableRequest;
import ua.com.alevel.hw_8_web_jdbc.persistence.datatable.DataTableResponse;
import ua.com.alevel.hw_8_web_jdbc.persistence.entity.Patient;

import java.util.List;

public interface PatientDao extends BaseDao<Patient> {
    DataTableResponse<Patient> findAllByDoctorId(DataTableRequest dataTableRequest, Long id);
}
