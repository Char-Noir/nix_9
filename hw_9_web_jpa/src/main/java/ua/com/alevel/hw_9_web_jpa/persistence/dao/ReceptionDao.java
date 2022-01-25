package ua.com.alevel.hw_9_web_jpa.persistence.dao;

import ua.com.alevel.hw_9_web_jpa.persistence.datatable.DataTableRequest;
import ua.com.alevel.hw_9_web_jpa.persistence.datatable.DataTableResponse;
import ua.com.alevel.hw_9_web_jpa.persistence.entity.Reception;

public interface ReceptionDao extends BaseDao<Reception> {

    DataTableResponse<Reception> findAllByPatientId(DataTableRequest dataTableRequest, Long id);

    long countByPatient(Long id);

    DataTableResponse<Reception> findAllByDoctorId(DataTableRequest dataTableRequest, Long id);

    long countByDoctor(Long id);
}
