package ua.com.alevel.web.dto.response.doctor;

import ua.com.alevel.persistence.entity.doctor.Doctor;
import ua.com.alevel.util.EnumsNamesConverterUtil;
import ua.com.alevel.web.dto.response.ResponseDto;
import ua.com.alevel.web.dto.response.user.UserResponseDto;

import java.time.LocalDate;

public class DoctorResponseDto extends ResponseDto {
    private String name;
    private Float assessmentOfCertification;
    private String note;
    private LocalDate dateOfCertification;
    private String category;
    private String specialization;
    private UserResponseDto user;

    public DoctorResponseDto(Doctor doctor) {
        super(doctor.getId());
        this.assessmentOfCertification = doctor.getAssessmentOfCertification();
        this.dateOfCertification = doctor.getDateOfCertification();
        this.note = doctor.getDoctorNote();
        this.name = doctor.getName();
        this.user = new UserResponseDto(doctor.getUser());
        this.category = EnumsNamesConverterUtil.getString(doctor.getCategory());
        this.specialization = EnumsNamesConverterUtil.getString(doctor.getSpecialization());
    }

    public String getName() {
        return name;
    }

    public DoctorResponseDto setName(String name) {
        this.name = name;
        return this;
    }

    public Float getAssessmentOfCertification() {
        return assessmentOfCertification;
    }

    public DoctorResponseDto setAssessmentOfCertification(Float assessmentOfCertification) {
        this.assessmentOfCertification = assessmentOfCertification;
        return this;
    }

    public String getNote() {
        return note;
    }

    public DoctorResponseDto setNote(String note) {
        this.note = note;
        return this;
    }

    public LocalDate getDateOfCertification() {
        return dateOfCertification;
    }

    public DoctorResponseDto setDateOfCertification(LocalDate dateOfCertification) {
        this.dateOfCertification = dateOfCertification;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public DoctorResponseDto setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getSpecialization() {
        return specialization;
    }

    public DoctorResponseDto setSpecialization(String specialization) {
        this.specialization = specialization;
        return this;
    }

    public UserResponseDto getUser() {
        return user;
    }

    public DoctorResponseDto setUser(UserResponseDto user) {
        this.user = user;
        return this;
    }
}
