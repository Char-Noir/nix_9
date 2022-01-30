package ua.com.alevel.web.dto.request.doctor;

import org.springframework.format.annotation.DateTimeFormat;
import ua.com.alevel.persistence.entity.doctor.Category;
import ua.com.alevel.persistence.entity.doctor.Doctor;
import ua.com.alevel.persistence.entity.doctor.Specialization;
import ua.com.alevel.persistence.entity.user.User;
import ua.com.alevel.web.dto.request.RequestDto;
import ua.com.alevel.web.dto.request.user.UserRequestDto;

import java.time.LocalDate;

public class DoctorRequestDto extends RequestDto {
    private String name;
    private Float assessmentOfSertification;
    private String note;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfCertification;
    private Long category;
    private Long specialization;
    private Long user;

    public String getName() {
        return name;
    }

    public DoctorRequestDto setName(String name) {
        this.name = name;
        return this;
    }

    public Float getAssessmentOfSertification() {
        return assessmentOfSertification;
    }

    public DoctorRequestDto setAssessmentOfSertification(Float assessmentOfSertification) {
        this.assessmentOfSertification = assessmentOfSertification;
        return this;
    }

    public String getNote() {
        return note;
    }

    public DoctorRequestDto setNote(String note) {
        this.note = note;
        return this;
    }

    public LocalDate getDateOfCertification() {
        return dateOfCertification;
    }

    public DoctorRequestDto setDateOfCertification(LocalDate dateOfCertification) {
        this.dateOfCertification = dateOfCertification;
        return this;
    }

    public Long getCategory() {
        return category;
    }

    public DoctorRequestDto setCategory(Long category) {
        this.category = category;
        return this;
    }

    public Long getSpecialization() {
        return specialization;
    }

    public DoctorRequestDto setSpecialization(Long specialization) {
        this.specialization = specialization;
        return this;
    }

    public Long getUser() {
        return user;
    }

    public DoctorRequestDto setUser(Long user) {
        this.user = user;
        return this;
    }

    public Doctor getDoctor() {
        Doctor doctor = new Doctor();
        doctor.setDoctorNote(note);
        doctor.setAssessmentOfCertification(assessmentOfSertification);
        doctor.setName(name);
        doctor.setDateOfCertification(dateOfCertification);
        doctor.setCategory(new Category(category));
        doctor.setSpecialization(new Specialization(specialization));
        doctor.setUser(new User(user));
        return doctor;
    }
}
