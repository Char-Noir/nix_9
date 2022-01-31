package ua.com.alevel.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.logger.LoggerLevel;
import ua.com.alevel.logger.LoggerService;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.reception.Reception;
import ua.com.alevel.persistence.repository.reception.ReceptionRepository;
import ua.com.alevel.persistence.type.RoleType;
import ua.com.alevel.service.ReceptionService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReceptionServiceImpl implements ReceptionService {

    private final ReceptionRepository receptionRepository;
    private final CrudRepositoryHelper<Reception, ReceptionRepository> crudRepositoryHelper;
    private final LoggerService loggerService;

    public ReceptionServiceImpl(ReceptionRepository receptionRepository, CrudRepositoryHelper<Reception, ReceptionRepository> crudRepositoryHelper, LoggerService loggerService) {
        this.receptionRepository = receptionRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
        this.loggerService = loggerService;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    public void create(Reception entity) {
        loggerService.commit(LoggerLevel.INFO, "Reception starts created!");
        receptionRepository.save(entity);
        loggerService.commit(LoggerLevel.INFO, "Reception ends created!");
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void update(Reception entity) {
        loggerService.commit(LoggerLevel.INFO, "Reception starts updated!:" + entity.getId());
        if (receptionRepository.existsById(entity.getId())) {
            receptionRepository.save(entity);
            loggerService.commit(LoggerLevel.INFO, "Reception ends created!");
            return;
        }
        loggerService.commit(LoggerLevel.ERROR, "No such Reception");
        throw new EntityNotFoundException("No such Reception");
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void delete(Long id) {
        loggerService.commit(LoggerLevel.INFO, "Reception starts deleted!:" + id);
        if (receptionRepository.existsById(id)) {
            receptionRepository.delete(receptionRepository.getById(id));
            loggerService.commit(LoggerLevel.INFO, "Reception ends deleted!");
            return;
        }
        loggerService.commit(LoggerLevel.ERROR, "No such Reception");
        throw new EntityNotFoundException("No such Reception");

    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Reception> findById(Long id) {
        loggerService.commit(LoggerLevel.INFO, "Reception find by id!");
        return crudRepositoryHelper.findById(receptionRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<Reception> findAll(DataTableRequest request) {
        loggerService.commit(LoggerLevel.INFO, "Reception starts finding all!");
        Sort sort = request.getOrder().equals("desc")
                ? Sort.by(request.getSort()).descending()
                : Sort.by(request.getSort()).ascending();
        Page<Reception> entityPage = receptionRepository.findAll(
                PageRequest.of(request.getPage() - 1, request.getSize(), sort));
        DataTableResponse<Reception> dataTableResponse = new DataTableResponse<>();
        dataTableResponse.setCurrentPage(request.getPage());
        dataTableResponse.setPageSize(request.getSize());
        dataTableResponse.setOrder(request.getOrder());
        dataTableResponse.setSort(request.getSort());
        dataTableResponse.setItemsSize(entityPage.getTotalElements());
        dataTableResponse.setTotalPages(entityPage.getTotalPages());
        dataTableResponse.setItems(entityPage.getContent());
        loggerService.commit(LoggerLevel.INFO, "Reception ends finding all!");
        return dataTableResponse;
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        loggerService.commit(LoggerLevel.INFO, "Reception count!");
        return receptionRepository.count();
    }

    @Override
    public DataTableResponse<Reception> findAllByPatient(Long id, DataTableRequest request) {
        loggerService.commit(LoggerLevel.INFO, "Reception starts finding all by patient id: " + id);
        Sort sort = request.getOrder().equals("desc")
                ? Sort.by(request.getSort()).descending()
                : Sort.by(request.getSort()).ascending();
        Page<Reception> entityPage = receptionRepository.findAllByPatient_Id(id,
                PageRequest.of(request.getPage() - 1, request.getSize(), sort));
        DataTableResponse<Reception> dataTableResponse = new DataTableResponse<>();
        dataTableResponse.setCurrentPage(request.getPage());
        dataTableResponse.setPageSize(request.getSize());
        dataTableResponse.setOrder(request.getOrder());
        dataTableResponse.setSort(request.getSort());
        dataTableResponse.setItemsSize(entityPage.getTotalElements());
        dataTableResponse.setTotalPages(entityPage.getTotalPages());
        dataTableResponse.setItems(entityPage.getContent());
        loggerService.commit(LoggerLevel.INFO, "Reception ends finding all by patient id!");
        return dataTableResponse;
    }

    @Override
    public DataTableResponse<Reception> findAllByDoctor(Long id, DataTableRequest request) {
        loggerService.commit(LoggerLevel.INFO, "Reception starts finding all by doctor id: " + id);
        Sort sort = request.getOrder().equals("desc")
                ? Sort.by(request.getSort()).descending()
                : Sort.by(request.getSort()).ascending();
        Page<Reception> entityPage = receptionRepository.findAllByDoctor_Id(id,
                PageRequest.of(request.getPage() - 1, request.getSize(), sort));
        DataTableResponse<Reception> dataTableResponse = new DataTableResponse<>();
        dataTableResponse.setCurrentPage(request.getPage());
        dataTableResponse.setPageSize(request.getSize());
        dataTableResponse.setOrder(request.getOrder());
        dataTableResponse.setSort(request.getSort());
        dataTableResponse.setItemsSize(entityPage.getTotalElements());
        dataTableResponse.setTotalPages(entityPage.getTotalPages());
        dataTableResponse.setItems(entityPage.getContent());
        loggerService.commit(LoggerLevel.INFO, "Reception ends finding all by doctor id!");
        return dataTableResponse;
    }

    @Override
    public List<Reception> findAllByDate(LocalDate localDate) {
        return receptionRepository.findAllByDateOfReceptionBetween(localDate.minusDays(1), localDate.plusDays(1)).stream().filter(reception -> reception.getPatient().getIdUser().getRoleType() != RoleType.ROLE_DELETED).toList();
    }
}
