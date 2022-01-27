package ua.com.alevel.hw_10_web_repository.facade;

import ua.com.alevel.hw_10_web_repository.dto.impl.doctor.DoctorFullResponseDto;
import ua.com.alevel.hw_10_web_repository.dto.impl.doctor.DoctorRequestDto;
import ua.com.alevel.hw_10_web_repository.dto.impl.doctor.DoctorResponseDto;

public interface DoctorFacade extends BaseFacade<DoctorRequestDto, DoctorResponseDto, DoctorFullResponseDto> {
    DoctorFullResponseDto findDoctorByPatientId(Long id);
}
