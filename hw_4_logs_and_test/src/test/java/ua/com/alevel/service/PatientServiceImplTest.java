package ua.com.alevel.service;

import org.junit.jupiter.api.*;
import ua.com.alevel.entity.Patient;
import ua.com.alevel.service.impl.PatientServiceImpl;

import static ua.com.alevel.service.TestUtil.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PatientServiceImplTest {

    private final static PatientService patientServiceImpl = new PatientServiceImpl();

    @BeforeAll
    static void initDBForTest() {
        for (int i = 0; i < COUNT_OF_USERS; i++) {
            Patient patient = TestUtil.generatePatient(TestUtil.PATIENT_NAME + i);
            patientServiceImpl.create(patient);
        }
        verifyPatientArrayWhenPatientsListIsNotEmpty(COUNT_OF_USERS);
    }

    @Order(1)
    @Test
    public void shouldCreatePatientWhenNameIsEmpty() {
        Patient patient = TestUtil.generatePatient(TestUtil.PATIENT_NAME + 0).setName(null);
        patientServiceImpl.create(patient);
        verifyPatientArrayWhenPatientsListIsNotEmpty(COUNT_OF_USERS + 1);
    }

    @Order(2)
    @Test
    public void shouldBeDeletePatient() {
        Long id = getRandomIdFromPatientList();
        patientServiceImpl.delete(id);
        verifyPatientArrayWhenPatientsListIsNotEmpty(COUNT_OF_USERS + 1);
    }

    @Order(3)
    @Test
    public void shouldBeFindPatientWhenIdIsRandom() {
        Patient patient = getPatientFromPatientList(getRandomIdFromPatientList());
        Assertions.assertNotNull(patient);
    }

    @Order(4)
    @Test
    public void shouldBeUpdatePatient() {
        Long id = getRandomIdFromPatientList();
        Patient patient = getPatientFromPatientList(id);
        patient.setName("test");
        patientServiceImpl.update(patient);
        patient = patientServiceImpl.findById(id);
        Assertions.assertEquals("test", patient.getName());
    }

    @Order(5)
    @Test
    public void shouldBeNonEmptyArrayWhenAllPatientsDeleted() {
        TestUtil.deleteAllPatients();
        verifyPatientArrayWhenPatientsListIsNotEmpty(COUNT_OF_USERS + 1);
    }

    private static void verifyPatientArrayWhenPatientsListIsNotEmpty(int size) {
        int sizePatients = patientServiceImpl.findAll().length;
        Assertions.assertTrue(sizePatients != 0);
        Assertions.assertEquals(size, sizePatients);
    }
}
