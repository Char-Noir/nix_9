package ua.com.alevel.hw_8_web_jdbc.facade;

import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.hw_8_web_jdbc.dto.impl.patient.PatientFullResponseDto;
import ua.com.alevel.hw_8_web_jdbc.dto.impl.patient.PatientRequestDto;
import ua.com.alevel.hw_8_web_jdbc.dto.impl.patient.PatientResponseDto;
import ua.com.alevel.hw_8_web_jdbc.dto.impl.reception.ReceptionResponseDto;
import ua.com.alevel.hw_8_web_jdbc.view.PageData;

import java.util.List;

public interface PatientFacade extends BaseFacade<PatientRequestDto, PatientResponseDto, PatientFullResponseDto> {
    PageData<PatientResponseDto> findAllByDoctorId(WebRequest webRequest, Long id);
}