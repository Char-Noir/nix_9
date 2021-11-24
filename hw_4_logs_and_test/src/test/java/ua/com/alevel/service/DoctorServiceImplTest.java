package ua.com.alevel.service;

import org.junit.jupiter.api.*;
import ua.com.alevel.entity.Doctor;
import ua.com.alevel.service.impl.DoctorServiceImpl;

import static ua.com.alevel.service.TestUtil.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DoctorServiceImplTest {

    private final static DoctorService doctorService = new DoctorServiceImpl();

    @BeforeAll
    static void initDBForTest() {
        for (int i = 0; i < COUNT_OF_USERS; i++) {
            Doctor doctor = TestUtil.generateDoctor(TestUtil.DOCTOR_NAME + i);
            doctorService.create(doctor);
        }
        verifyDoctorArrayWhenDoctorsListIsNotEmpty(COUNT_OF_USERS);
    }

    private static void verifyDoctorArrayWhenDoctorsListIsNotEmpty(int size) {
        int sizeDoctors = doctorService.findAll().length;
        Assertions.assertTrue(sizeDoctors != 0);
        Assertions.assertEquals(size, sizeDoctors);
    }

    @Order(1)
    @Test
    public void shouldCreateDoctorWhenNameIsEmpty() {
        Doctor doctor = TestUtil.generateDoctor(TestUtil.DOCTOR_NAME + 0).setName(null);
        doctorService.create(doctor);
        verifyDoctorArrayWhenDoctorsListIsNotEmpty(COUNT_OF_USERS + 1);
    }

    @Order(2)
    @Test
    public void shouldBeDeleteDoctor() {
        Long id = getRandomIdFromDoctorList();
        doctorService.delete(id);
        verifyDoctorArrayWhenDoctorsListIsNotEmpty(COUNT_OF_USERS + 1);
    }

    @Order(3)
    @Test
    public void shouldBeFindDoctorWhenIdIsRandom() {
        Doctor doctor = getDoctorFromDoctorList(getRandomIdFromDoctorList());
        Assertions.assertNotNull(doctor);
    }

    @Order(4)
    @Test
    public void shouldBeUpdateDoctor() {
        Long id = getRandomIdFromDoctorList();
        Doctor doctor = getDoctorFromDoctorList(id);
        doctor.setName("test");
        doctorService.update(doctor);
        doctor = doctorService.findById(id);
        Assertions.assertEquals("test", doctor.getName());
    }

    @Order(5)
    @Test
    public void shouldBeNonEmptyArrayWhenAllDoctorsDeleted() {
        TestUtil.deleteAllDoctors();
        verifyDoctorArrayWhenDoctorsListIsNotEmpty(COUNT_OF_USERS + 1);
    }
}
