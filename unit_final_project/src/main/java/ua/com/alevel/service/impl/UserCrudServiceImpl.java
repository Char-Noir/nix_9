package ua.com.alevel.service.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.exception.EntityExistException;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.user.User;
import ua.com.alevel.persistence.repository.user.UserRepository;
import ua.com.alevel.persistence.type.RoleType;
import ua.com.alevel.service.DoctorService;
import ua.com.alevel.service.PatientService;
import ua.com.alevel.service.UserCrudService;

import java.util.Optional;

@Service
public class UserCrudServiceImpl implements UserCrudService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository<User> personalRepository;
    private final CrudRepositoryHelper<User, UserRepository<User>> crudRepositoryHelper;
    private final PatientService patientService;
    private final DoctorService doctorService;

    public UserCrudServiceImpl(
            BCryptPasswordEncoder bCryptPasswordEncoder,
            UserRepository<User> personalRepository, CrudRepositoryHelper<User, UserRepository<User>> crudRepositoryHelper, PatientService patientService, DoctorService doctorService) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.personalRepository = personalRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
        this.patientService = patientService;
        this.doctorService = doctorService;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    public void create(User entity) {
        if (personalRepository.existsByEmail(entity.getEmail())) {
            throw new EntityExistException("this user is exist");
        }
        entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
        crudRepositoryHelper.create(personalRepository, entity);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void update(User entity) {
        if (personalRepository.existsById(entity.getId())) {
            entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
            personalRepository.save(entity);
        }
        throw new EntityNotFoundException("No such user");
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void delete(Long id) {
        Optional<User> optional = personalRepository.findById(id);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException("No such user");
        }
        User user = optional.get();
        patientService.findByUserId(user.getId()).ifPresent(patient -> patientService.delete(patient.getId()));
        doctorService.findByUserId(user.getId()).ifPresent(doctor -> doctorService.delete(doctor.getId()));
        user.setRoleType(RoleType.ROLE_DELETED);
        user.setPassword("DELETED");
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {
        return crudRepositoryHelper.findById(personalRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<User> findAll(DataTableRequest request) {
        return crudRepositoryHelper.findAll(personalRepository, request);
    }

    @Override
    public long count() {
        return personalRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findByUsername(String username) {
        return personalRepository.findByEmail(username);
    }
}
