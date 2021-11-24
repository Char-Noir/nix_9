package ua.com.alevel.service;

import ua.com.alevel.entity.Doctor;
import ua.com.alevel.entity.Patient;
import ua.com.alevel.entity.Reception;
import ua.com.alevel.entity.Status;
import ua.com.alevel.service.impl.DoctorServiceImpl;
import ua.com.alevel.service.impl.PatientServiceImpl;
import ua.com.alevel.service.impl.ReceptionServiceImpl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;

public class TestUtil {
    public static final int COUNT_OF_USERS = 30;
    public static final int COUNT_OF_RECEPTIONS = 12;
    public static final String PATIENT_NAME = "Patient";
    public static final String DOCTOR_NAME = "Doctor";
    private static final PatientService patientService = new PatientServiceImpl();
    private static final DoctorService doctorService = new DoctorServiceImpl();
    private static final ReceptionService receptionService = new ReceptionServiceImpl();

    static Patient generatePatient(String name) {
        Random random = new Random();
        return new Patient()
                .setName(name)
                .setDateOfBirth(LocalDate.of(random.nextInt(1996, 2200), random.nextInt(1, 12), random.nextInt(1, 28)))
                .setPhoneNumber(String.valueOf(random.nextInt(100000000, 999999999)))
                .setUserDocuments(String.valueOf(random.nextInt(999999999)))
                .setLogin("login" + name)
                .setHashPassword(String.valueOf(random.nextInt(999999999)))
                .setStatus(Status.Patient);

    }

    static Doctor generateDoctor(String name) {
        Random random = new Random();
        return new Doctor()
                .setName(name)
                .setAssessmentOfCertification(random.nextDouble(100))
                .setDoctorNote(String.valueOf(random.nextInt(999999999)))
                .setDateOfCertification(LocalDate.of(random.nextInt(1996, 2200), random.nextInt(1, 12), random.nextInt(1, 28)))
                .setLogin("login" + name)
                .setHashPassword(String.valueOf(random.nextInt(999999999)))
                .setStatus(Status.Doctor);
    }

    public static void deleteAllPatients() {
        for (Patient patient :
                patientService.findAll()) {
            patientService.delete(patient.getId());
        }
    }

    public static void deleteAllDoctors() {
        for (Doctor doctor :
                doctorService.findAll()) {
            doctorService.delete(doctor.getId());
        }
    }


    public static Long getRandomIdFromDoctorList() {
        return doctorService.findAll()[0].getId();
    }

    public static Doctor getDoctorFromDoctorList(Long id) {
        return doctorService.findById(id);
    }

    static Long getRandomIdFromPatientList() {
        return patientService.findAll()[0].getId();
    }

    static Patient getPatientFromPatientList(Long id) {
        return patientService.findById(id);
    }

    static Long getRandomIdFromReceptionList() {
        return receptionService.findAll()[0].getId();
    }

    static Reception getReceptionFromReceptionList(Long id) {
        return receptionService.findById(id);
    }

    public static Reception generateReception() {
        Random random = new Random();
        Patient patient = getPatientFromPatientList(getRandomIdFromPatientList());
        Doctor doctor = getDoctorFromDoctorList(getRandomIdFromDoctorList());
        return new Reception()
                .setDateOfReception(LocalDate.of(random.nextInt(1996, 2200), random.nextInt(1, 12), random.nextInt(1, 28)))
                .setReceptionTime(LocalTime.of(random.nextInt(1, 23), random.nextInt(1, 59)))
                .setReviewComment(String.valueOf(random.nextInt(999999999)))
                .setReceptionPrice(random.nextDouble())
                .setDoctorId(doctor.getId())
                .setPatientId(patient.getId());
    }

    public static void deleteAllReceptions() {
        for (Reception reception :
                receptionService.findAll()) {
            receptionService.delete(reception.getId());
        }
    }
}
