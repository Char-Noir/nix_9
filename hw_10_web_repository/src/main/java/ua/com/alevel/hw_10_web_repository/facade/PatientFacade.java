package ua.com.alevel.hw_10_web_repository.facade;

import ua.com.alevel.hw_10_web_repository.dto.impl.patient.PatientFullResponseDto;
import ua.com.alevel.hw_10_web_repository.dto.impl.patient.PatientRequestDto;
import ua.com.alevel.hw_10_web_repository.dto.impl.patient.PatientResponseDto;

public interface PatientFacade extends BaseFacade<PatientRequestDto, PatientResponseDto, PatientFullResponseDto> {

}