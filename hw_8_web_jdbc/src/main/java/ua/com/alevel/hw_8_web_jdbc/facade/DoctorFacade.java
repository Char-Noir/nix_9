package ua.com.alevel.hw_8_web_jdbc.facade;

import ua.com.alevel.hw_8_web_jdbc.dto.impl.doctor.DoctorFullResponseDto;
import ua.com.alevel.hw_8_web_jdbc.dto.impl.doctor.DoctorRequestDto;
import ua.com.alevel.hw_8_web_jdbc.dto.impl.doctor.DoctorResponseDto;
import ua.com.alevel.hw_8_web_jdbc.persistence.entity.Doctor;

public interface DoctorFacade extends BaseFacade<DoctorRequestDto, DoctorResponseDto, DoctorFullResponseDto> {
    DoctorFullResponseDto findDoctorByPatientId(Long id);
}
