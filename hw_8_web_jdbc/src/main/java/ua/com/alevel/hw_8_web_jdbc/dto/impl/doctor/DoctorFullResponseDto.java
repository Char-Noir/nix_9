package ua.com.alevel.hw_8_web_jdbc.dto.impl.doctor;

import ua.com.alevel.hw_8_web_jdbc.persistence.entity.Category;
import ua.com.alevel.hw_8_web_jdbc.persistence.entity.Doctor;
import ua.com.alevel.hw_8_web_jdbc.persistence.entity.Specialization;
import ua.com.alevel.hw_8_web_jdbc.persistence.entity.Status;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public final class DoctorFullResponseDto extends DoctorResponseDto {

    private final Double assessmentOfCertification;
    private final String note;
    private final String login;

    public String getPassword() {
        return password;
    }

    private final String password;

    public DoctorFullResponseDto(String name, LocalDate dateOfCertification, Category category, Specialization specialization, Status status, Double assessmentOfCertification, String note, String login, String password) {
        super(name, dateOfCertification, category, specialization, status);
        this.assessmentOfCertification = assessmentOfCertification;
        this.note = note;
        this.login = login;
        this.password = password;
    }

    public DoctorFullResponseDto(Doctor doctor) {
        super(doctor);
        this.assessmentOfCertification = doctor.getAssessmentOfCertification();
        this.note = doctor.getDoctorNote();
        this.login = doctor.getLogin();
        this.password = doctor.getHashPassword();
    }

    @Override
    public String toString() {
        return "DoctorFullResponseDto{" +
                "id=" + id +
                ", assessmentOfCertification=" + assessmentOfCertification +
                ", note='" + note + '\'' +
                ", login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", dateOfCertification=" + dateOfCertification +
                ", category=" + category +
                ", specialization=" + specialization +
                ", status=" + status +
                '}';
    }

    public Double getAssessmentOfCertification() {
        return assessmentOfCertification;
    }

    public String getNote() {
        return note;
    }

    public String getLogin() {
        return login;
    }

    public Doctor getDoctor() {
        return new Doctor(id)
                .setName(name)
                .setAssessmentOfCertification(assessmentOfCertification)
                .setDateOfCertification(dateOfCertification)
                .setHashPassword(password)
                .setLogin(login)
                .setSpecialization(specialization)
                .setCategory(category)
                .setStatus(status)
                .setDoctorNote(note);
    }


}
