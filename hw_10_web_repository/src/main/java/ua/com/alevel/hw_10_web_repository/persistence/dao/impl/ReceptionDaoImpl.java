package ua.com.alevel.hw_10_web_repository.persistence.dao.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.hw_10_web_repository.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.hw_10_web_repository.persistence.dao.ReceptionDao;
import ua.com.alevel.hw_10_web_repository.persistence.datatable.DataTableRequest;
import ua.com.alevel.hw_10_web_repository.persistence.datatable.DataTableResponse;
import ua.com.alevel.hw_10_web_repository.persistence.entity.Reception;
import ua.com.alevel.hw_10_web_repository.persistence.repository.ReceptionRepository;

import java.util.Optional;


@Service
public class ReceptionDaoImpl implements ReceptionDao {


    private final CrudRepositoryHelper<Reception, ReceptionRepository> repositoryHelper;
    private final ReceptionRepository receptionRepository;

    public ReceptionDaoImpl(CrudRepositoryHelper<Reception, ReceptionRepository> repositoryHelper, ReceptionRepository receptionRepository) {
        this.repositoryHelper = repositoryHelper;
        this.receptionRepository = receptionRepository;
    }


    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.NEVER)
    public void create(Reception entity) {
        repositoryHelper.create(receptionRepository, entity);
    }


    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void update(Reception entity) {
        repositoryHelper.checkExist(receptionRepository, entity.getIdReception());
        receptionRepository.save(entity);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void delete(Long id) {
        repositoryHelper.delete(receptionRepository, id);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Reception> findById(Long id) {
        return repositoryHelper.findById(receptionRepository, id);
    }


    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<Reception> findAll(DataTableRequest request) {
        return repositoryHelper.findAll(receptionRepository, request, Reception.class);
    }

    @Override
    public DataTableResponse<Reception> findAllByPatientId(DataTableRequest dataTableRequest, Long id) {
        int page = dataTableRequest.getCurrentPage() - 1;
        int size = dataTableRequest.getPageSize();
        String sortParam = dataTableRequest.getSort();
        String orderParam = dataTableRequest.getOrder();

        Sort sort = orderParam.equals("desc")
                ? Sort.by(sortParam).descending()
                : Sort.by(sortParam).ascending();


        PageRequest request = PageRequest.of(page, size, sort);

        Page<Reception> pageEntity;

        pageEntity = receptionRepository.findReceptionsByPatient_IdPatient(id, request);


        DataTableResponse<Reception> dataTableResponse = new DataTableResponse<>();
        dataTableResponse.setSort(sortParam);
        dataTableResponse.setOrder(orderParam);
        dataTableResponse.setPageSize(size);
        dataTableResponse.setCurrentPage(page);
        dataTableResponse.setItemsSize(pageEntity.getTotalElements());
        dataTableResponse.setItemsSize(pageEntity.getTotalPages());
        dataTableResponse.setItems(pageEntity.getContent());

        return dataTableResponse;
    }


    @Override
    public DataTableResponse<Reception> findAllByDoctorId(DataTableRequest dataTableRequest, Long id) {
        int page = dataTableRequest.getCurrentPage() - 1;
        int size = dataTableRequest.getPageSize();
        String sortParam = dataTableRequest.getSort();
        String orderParam = dataTableRequest.getOrder();

        Sort sort = orderParam.equals("desc")
                ? Sort.by(sortParam).descending()
                : Sort.by(sortParam).ascending();


        PageRequest request = PageRequest.of(page, size, sort);

        Page<Reception> pageEntity;

        pageEntity = receptionRepository.findReceptionsByDoctor_idDoctor(id, request);


        DataTableResponse<Reception> dataTableResponse = new DataTableResponse<>();
        dataTableResponse.setSort(sortParam);
        dataTableResponse.setOrder(orderParam);
        dataTableResponse.setPageSize(size);
        dataTableResponse.setCurrentPage(page);
        dataTableResponse.setItemsSize(pageEntity.getTotalElements());
        dataTableResponse.setItemsSize(pageEntity.getTotalPages());
        dataTableResponse.setItems(pageEntity.getContent());

        return dataTableResponse;
    }

}
