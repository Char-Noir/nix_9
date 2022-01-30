package ua.com.alevel.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.patient.Patient;
import ua.com.alevel.persistence.entity.user.User;
import ua.com.alevel.persistence.repository.patient.PatientRepository;
import ua.com.alevel.persistence.repository.user.UserRepository;
import ua.com.alevel.persistence.type.RoleType;
import ua.com.alevel.service.PatientService;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final CrudRepositoryHelper<Patient, PatientRepository> crudRepositoryHelper;
    private final UserRepository<User> personalRepository;
    private final CrudRepositoryHelper<User, UserRepository<User>> userCrudRepositoryHelper;

    public PatientServiceImpl(PatientRepository patientRepository, CrudRepositoryHelper<Patient, PatientRepository> crudRepositoryHelper, UserRepository<User> personalRepository, CrudRepositoryHelper<User, UserRepository<User>> userCrudRepositoryHelper) {
        this.patientRepository = patientRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
        this.personalRepository = personalRepository;
        this.userCrudRepositoryHelper = userCrudRepositoryHelper;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    public void create(Patient entity) {
        if (entity.getIdUser() == null) {
            throw new EntityNotFoundException("Cannot add patient to unavailable user");
        }
        if (entity.getIdUser().getId() == null) {
            throw new EntityNotFoundException("Cannot add patient to unavailable user");
        }
        userCrudRepositoryHelper.checkExist(personalRepository, entity.getIdUser().getId());
        Optional<User> optional = userCrudRepositoryHelper.findById(personalRepository, entity.getIdUser().getId());
        if (optional.isEmpty()) {
            throw new EntityNotFoundException("Cannot add patient to unavailable user");
        }
        User user = optional.get();
        if (user.getRoleType() == RoleType.ROLE_ADMIN || user.getRoleType() == RoleType.ROLE_DELETED || user.getRoleType() == RoleType.ROLE_PATIENT_DOCTOR || user.getRoleType() == RoleType.ROLE_PATIENT) {
            throw new RuntimeException("Cannot add patient because of your role");
        }
        entity.setIdUser(optional.get());
        patientRepository.save(entity);

        if (user.getRoleType() == RoleType.ROLE_DOCTOR) {
            user.setRoleType(RoleType.ROLE_PATIENT_DOCTOR);
        } else if (user.getRoleType() == RoleType.ROLE_PERSONAL) {
            user.setRoleType(RoleType.ROLE_PATIENT);
        }
        personalRepository.save(user);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void update(Patient entity) {
        if (patientRepository.existsById(entity.getId())) {
            patientRepository.save(entity);
        }
        throw new EntityNotFoundException("No such user");
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void delete(Long id) {
        Optional<Patient> optional = patientRepository.findById(id);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException("No such user");
        }
        Patient patient = optional.get();
        patient.setDateOfBirth(LocalDate.EPOCH);
        patient.setPhoneNumber("DELETED");
        patient.setUserDocuments("DELETED");
        patient.setName("DELETED");
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Patient> findById(Long id) {
        return crudRepositoryHelper.findById(patientRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<Patient> findAll(DataTableRequest request) {
        Sort sort = request.getOrder().equals("desc")
                ? Sort.by(request.getSort()).descending()
                : Sort.by(request.getSort()).ascending();
        Page<Patient> entityPage = patientRepository.findAllByUser_RoleTypeNot(RoleType.ROLE_DELETED,
                PageRequest.of(request.getPage() - 1, request.getSize(), sort));
        DataTableResponse<Patient> dataTableResponse = new DataTableResponse<>();
        dataTableResponse.setCurrentPage(request.getPage());
        dataTableResponse.setPageSize(request.getSize());
        dataTableResponse.setOrder(request.getOrder());
        dataTableResponse.setSort(request.getSort());
        dataTableResponse.setItemsSize(entityPage.getTotalElements());
        dataTableResponse.setTotalPages(entityPage.getTotalPages());
        dataTableResponse.setItems(entityPage.getContent());
        return dataTableResponse;
    }

    @Override
    public long count() {
        return patientRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Patient> findByUserId(Long id) {
        return patientRepository.findByUserId(id);
    }
}
