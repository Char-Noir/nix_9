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

    public DoctorServiceImpl(DoctorRepository doctorRepository, CrudRepositoryHelper<Doctor, DoctorRepository> crudRepositoryHelper, UserRepository<User> personalRepository, CrudRepositoryHelper<User, UserRepository<User>> userCrudRepositoryHelper) {
        this.doctorRepository = doctorRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
        this.personalRepository = personalRepository;
        this.userCrudRepositoryHelper = userCrudRepositoryHelper;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    public void create(Doctor entity) {
        if (entity.getUser() == null) {
            throw new EntityNotFoundException("Cannot add doctor to unavailable user");
        }
        if (entity.getUser().getId() == null) {
            throw new EntityNotFoundException("Cannot add doctor to unavailable user");
        }
        userCrudRepositoryHelper.checkExist(personalRepository, entity.getUser().getId());
        Optional<User> optional = userCrudRepositoryHelper.findById(personalRepository, entity.getUser().getId());
        if (optional.isEmpty()) {
            throw new EntityNotFoundException("Cannot add doctor to unavailable user");
        }
        User user = optional.get();
        if (user.getRoleType() == RoleType.ROLE_ADMIN || user.getRoleType() == RoleType.ROLE_DELETED || user.getRoleType() == RoleType.ROLE_PATIENT_DOCTOR || user.getRoleType() == RoleType.ROLE_DOCTOR) {
            throw new RuntimeException("Cannot add patient because of your role");
        }
        entity.setUser(user);
        doctorRepository.save(entity);
        if (user.getRoleType() == RoleType.ROLE_PATIENT) {
            user.setRoleType(RoleType.ROLE_PATIENT_DOCTOR);
        } else if (user.getRoleType() == RoleType.ROLE_PERSONAL) {
            user.setRoleType(RoleType.ROLE_DOCTOR);
        }
        personalRepository.save(user);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void update(Doctor entity) {
        if (doctorRepository.existsById(entity.getId())) {
            doctorRepository.save(entity);
        }
        throw new EntityNotFoundException("No such user");
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void delete(Long id) {
        Optional<Doctor> optional = doctorRepository.findById(id);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException("No such user");
        }
        Doctor doctor = optional.get();
        doctor.setDoctorNote("DELETED");
        doctor.setAssessmentOfCertification(0f);
        doctor.setName("DELETED");
        doctor.setDateOfCertification(LocalDate.EPOCH);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Doctor> findById(Long id) {
        return crudRepositoryHelper.findById(doctorRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<Doctor> findAll(DataTableRequest request) {
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
        return dataTableResponse;
    }

    @Override
    public long count() {
        return doctorRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Doctor> findByUserId(Long id) {
        System.out.println(id);
        System.out.println(doctorRepository.findByUserId(id));
        return doctorRepository.findByUserId(id);
    }

    @Override
    public DataTableResponse<Doctor> findNotDeleted(DataTableRequest request) {
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
        return dataTableResponse;
    }
}
