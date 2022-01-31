package ua.com.alevel.service.impl;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.doctor.Category;
import ua.com.alevel.persistence.entity.doctor.Doctor;
import ua.com.alevel.persistence.entity.doctor.Specialization;
import ua.com.alevel.persistence.entity.user.User;
import ua.com.alevel.persistence.type.RoleType;
import ua.com.alevel.service.DoctorService;
import ua.com.alevel.service.UserCrudService;

import java.time.LocalDate;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
class DoctorServiceImplTest {

    @Autowired
    private  DoctorService doctorService;
    @Autowired
    private  UserCrudService userCrudService;



    @Test
    @Order(1)
    void create() {
        long count = doctorService.count();
        Doctor doctor = new Doctor();
        doctor.setCategory(new Category(10L));
        doctor.setDoctorNote("Note");
        doctor.setSpecialization(new Specialization(42L));
        doctor.setName("Name");
        doctor.setAssessmentOfCertification(0.5F);
        doctor.setDateOfCertification(LocalDate.now());
        doctor.setUser(getRandomUserByRole(RoleType.ROLE_PATIENT));
        doctorService.create(doctor);
        assertEquals(count + 1, doctorService.count());
    }

    @Test
    @Order(2)
    void update() {
        String name;
        DataTableResponse<Doctor> dataTableResponse = doctorService.findAll(new DataTableRequest());
        Doctor doctor = dataTableResponse.getItems().get(0);
        name = doctor.getName();
        doctor.setName("Test");
        doctorService.update(doctor);
        assertNotEquals(name, doctorService.findById(doctor.getId()).get().getName());
    }

    @Test
    @Order(3)
    void delete() {
        long count = doctorService.count();
        DataTableResponse<Doctor> dataTableResponse = doctorService.findAll(new DataTableRequest());
        Doctor doctor = dataTableResponse.getItems().get(0);
        doctorService.delete(doctor.getId());
        assertEquals(count, doctorService.count());
    }


    private User getRandomUserByRole(RoleType roleTYpe) {
        Random random = new Random();
        DataTableResponse<User> userResponseDtoPageData = userCrudService.findAll(new DataTableRequest());
        User user = userResponseDtoPageData.getItems().get(random.nextInt(0, userResponseDtoPageData.getItems().size() - 1));
        if (user.getRoleType() != roleTYpe) {
            user = getRandomUserByRole(roleTYpe);
        }
        return user;
    }
}