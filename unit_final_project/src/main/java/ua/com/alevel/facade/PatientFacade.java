package ua.com.alevel.facade;

import ua.com.alevel.web.dto.request.patient.PatientRequestDto;
import ua.com.alevel.web.dto.response.patient.PatientResponseDto;

public interface PatientFacade extends CrudFacade<PatientRequestDto, PatientResponseDto> {
    PatientResponseDto findByUserId(Long id);
}
