package ua.com.alevel.hw_10_web_repository.dto.impl.reception;

import org.springframework.format.annotation.DateTimeFormat;
import ua.com.alevel.hw_10_web_repository.dto.RequestDto;
import ua.com.alevel.hw_10_web_repository.persistence.entity.Reception;

import java.time.LocalDateTime;

public class ReceptionRequestDto extends RequestDto {
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    protected final LocalDateTime datetime;
    protected final String reviewComment;
    protected final Double price;
    protected final Long doctor;
    protected final Long patient;

    public ReceptionRequestDto(LocalDateTime datetime, String reviewComment, Double price, Long doctor, Long patient) {
        this.datetime = datetime;
        this.reviewComment = reviewComment;
        this.price = price;
        this.doctor = doctor;
        this.patient = patient;
    }

    public Reception getReception() {
        return new Reception()
                .setDateOfReception(datetime.toLocalDate())
                .setReceptionTime(datetime.toLocalTime())
                .setReviewComment(reviewComment)
                .setReceptionPrice(price)
                .setDoctorId(doctor)
                .setPatientId(patient);
    }

    @Override
    public String toString() {
        return "ReceptionRequestDto{" +
                "datetime=" + datetime +
                ", reviewComment='" + reviewComment + '\'' +
                ", price=" + price +
                ", doctor=" + doctor +
                ", patient=" + patient +
                '}';
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public String getReviewComment() {
        return reviewComment;
    }

    public Double getPrice() {
        return price;
    }

    public Long getDoctor() {
        return doctor;
    }

    public Long getPatient() {
        return patient;
    }
}
