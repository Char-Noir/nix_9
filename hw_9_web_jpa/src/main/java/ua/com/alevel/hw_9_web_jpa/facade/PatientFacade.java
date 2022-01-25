package ua.com.alevel.hw_9_web_jpa.facade;

import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.hw_9_web_jpa.dto.impl.patient.PatientFullResponseDto;
import ua.com.alevel.hw_9_web_jpa.dto.impl.patient.PatientRequestDto;
import ua.com.alevel.hw_9_web_jpa.dto.impl.patient.PatientResponseDto;
import ua.com.alevel.hw_9_web_jpa.view.PageData;

public interface PatientFacade extends BaseFacade<PatientRequestDto, PatientResponseDto, PatientFullResponseDto> {

}