package ua.com.alevel.service;

import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.reception.Reception;

import java.time.LocalDate;
import java.util.List;

public interface ReceptionService extends BaseCrudService<Reception> {
    DataTableResponse<Reception> findAllByPatient(Long id, DataTableRequest dataTableRequest);

    DataTableResponse<Reception> findAllByDoctor(Long id, DataTableRequest dataTableRequest);

    List<Reception> findAllByDate(LocalDate localDate);
}
