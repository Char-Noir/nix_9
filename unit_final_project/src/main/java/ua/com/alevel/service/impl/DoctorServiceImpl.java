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
import ua.com.alevel.persistence.entity.doctor.Doctor;
import ua.com.alevel.persistence.entity.user.User;
import ua.com.alevel.persistence.repository.doctor.DoctorRepository;
import ua.com.alevel.persistence.repository.user.UserRepository;
import ua.com.alevel.persistence.type.RoleType;
import ua.com.alevel.service.DoctorService;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final CrudRepositoryHelper<Doctor, DoctorRepository> crudRepositoryHelper;
    private final UserRepository<User> personalRepository;
    private final CrudRepositoryHelper<User, UserRepository<User>> userCrudRepositoryHelper;
    private final LoggerService loggerService;

    public DoctorServiceImpl(DoctorRepository doctorRepository, CrudRepositoryHelper<Doctor, DoctorRepository> crudRepositoryHelper, UserRepository<User> personalRepository, CrudRepositoryHelper<User, UserRepository<User>> userCrudRepositoryHelper, LoggerService loggerService) {
        this.doctorRepository = doctorRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
        this.personalRepository = personalRepository;
        this.userCrudRepositoryHelper = userCrudRepositoryHelper;
        this.loggerService = loggerService;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    public void create(Doctor entity) {
        loggerService.commit(LoggerLevel.INFO, "Doctor starts created!");
        if (entity.getUser() == null) {
            loggerService.commit(LoggerLevel.ERROR, "Cannot add doctor to unavailable user");
            throw new EntityNotFoundException("Cannot add doctor to unavailable user");
        }
        if (entity.getUser().getId() == null) {

            loggerService.commit(LoggerLevel.ERROR, "Cannot add doctor to unavailable user");
            throw new EntityNotFoundException("Cannot add doctor to unavailable user");
        }
        userCrudRepositoryHelper.checkExist(personalRepository, entity.getUser().getId());
        Optional<User> optional = userCrudRepositoryHelper.findById(personalRepository, entity.getUser().getId());
        if (optional.isEmpty()) {
            loggerService.commit(LoggerLevel.ERROR, "Cannot add doctor to unavailable user");
            throw new EntityNotFoundException("Cannot add doctor to unavailable user");
        }
        User user = optional.get();
        if (user.getRoleType() == RoleType.ROLE_ADMIN || user.getRoleType() == RoleType.ROLE_DELETED || user.getRoleType() == RoleType.ROLE_PATIENT_DOCTOR || user.getRoleType() == RoleType.ROLE_DOCTOR) {
            loggerService.commit(LoggerLevel.ERROR, "Cannot add patient because of your role");
            throw new RuntimeException("Cannot add patient because of your role");
        }
        entity.setUser(user);
        doctorRepository.save(entity);
        loggerService.commit(LoggerLevel.INFO, "Doctor ends created!");
        if (user.getRoleType() == RoleType.ROLE_PATIENT) {
            loggerService.commit(LoggerLevel.INFO, "Change user role Patient-> Patient_doctor");
            user.setRoleType(RoleType.ROLE_PATIENT_DOCTOR);
        } else if (user.getRoleType() == RoleType.ROLE_PERSONAL) {
            loggerService.commit(LoggerLevel.INFO, "Change user role Personal-> Doctor");
            user.setRoleType(RoleType.ROLE_DOCTOR);
        }
        personalRepository.save(user);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void update(Doctor entity) {
        loggerService.commit(LoggerLevel.INFO, "Doctor starts updated!:" + entity.getId());
        if (doctorRepository.existsById(entity.getId())) {
            doctorRepository.save(entity);
            loggerService.commit(LoggerLevel.INFO, "Doctor ends created!");
            return;
        }
        loggerService.commit(LoggerLevel.ERROR, "No such user");
        throw new EntityNotFoundException("No such user");
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void delete(Long id) {
        loggerService.commit(LoggerLevel.INFO, "Doctor starts deleted!:" + id);
        Optional<Doctor> optional = doctorRepository.findById(id);
        if (optional.isEmpty()) {
            loggerService.commit(LoggerLevel.ERROR, "No such user");
            throw new EntityNotFoundException("No such user");
        }
        Doctor doctor = optional.get();
        doctor.setDoctorNote("DELETED");
        doctor.setAssessmentOfCertification(0f);
        doctor.setName("DELETED");
        doctor.setDateOfCertification(LocalDate.EPOCH);
        loggerService.commit(LoggerLevel.INFO, "Doctor ends updated!");
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Doctor> findById(Long id) {
        loggerService.commit(LoggerLevel.INFO, "Doctor find by id!");
        return crudRepositoryHelper.findById(doctorRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<Doctor> findAll(DataTableRequest request) {
        loggerService.commit(LoggerLevel.INFO, "Doctor starts finding all!");
        Sort sort = request.getOrder().equals("desc")
                ? Sort.by(request.getSort()).descending()
                : Sort.by(request.getSort()).ascending();
        Page<Doctor> entityPage = doctorRepository.findAll(
                PageRequest.of(request.getPage() - 1, request.getSize(), sort));
        DataTableResponse<Doctor> dataTableResponse = new DataTableResponse<>();
        dataTableResponse.setCurrentPage(request.getPage());
        dataTableResponse.setPageSize(request.getSize());
        dataTableResponse.setOrder(request.getOrder());
        dataTableResponse.setSort(request.getSort());
        dataTableResponse.setItemsSize(entityPage.getTotalElements());
        dataTableResponse.setTotalPages(entityPage.getTotalPages());
        dataTableResponse.setItems(entityPage.getContent());
        loggerService.commit(LoggerLevel.INFO, "Doctor ends finding all!");
        return dataTableResponse;
    }

    @Override
    public long count() {
        loggerService.commit(LoggerLevel.INFO, "Doctor count!");
        return doctorRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Doctor> findByUserId(Long id) {
        loggerService.commit(LoggerLevel.INFO, "Doctor find by user id!");
        return doctorRepository.findByUserId(id);
    }

    @Override
    public DataTableResponse<Doctor> findNotDeleted(DataTableRequest request) {
        loggerService.commit(LoggerLevel.INFO, "Doctor starts find all not deleted!");
        Sort sort = request.getOrder().equals("desc")
                ? Sort.by(request.getSort()).descending()
                : Sort.by(request.getSort()).ascending();
        Page<Doctor> entityPage = doctorRepository.findAllByUser_RoleTypeNot(RoleType.ROLE_DELETED,
                PageRequest.of(request.getPage() - 1, request.getSize(), sort));
        DataTableResponse<Doctor> dataTableResponse = new DataTableResponse<>();
        dataTableResponse.setCurrentPage(request.getPage());
        dataTableResponse.setPageSize(request.getSize());
        dataTableResponse.setOrder(request.getOrder());
        dataTableResponse.setSort(request.getSort());
        dataTableResponse.setItemsSize(entityPage.getTotalElements());
        dataTableResponse.setTotalPages(entityPage.getTotalPages());
        dataTableResponse.setItems(entityPage.getContent());
        loggerService.commit(LoggerLevel.INFO, "Doctor ends find all not deleted!");
        return dataTableResponse;
    }
}
