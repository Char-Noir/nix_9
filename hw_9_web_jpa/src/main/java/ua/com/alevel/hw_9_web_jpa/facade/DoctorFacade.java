package ua.com.alevel.hw_9_web_jpa.facade;

import ua.com.alevel.hw_9_web_jpa.dto.impl.doctor.DoctorFullResponseDto;
import ua.com.alevel.hw_9_web_jpa.dto.impl.doctor.DoctorRequestDto;
import ua.com.alevel.hw_9_web_jpa.dto.impl.doctor.DoctorResponseDto;

public interface DoctorFacade extends BaseFacade<DoctorRequestDto, DoctorResponseDto, DoctorFullResponseDto> {
    DoctorFullResponseDto findDoctorByPatientId(Long id);
}
