package ua.com.alevel.facade;

import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.web.dto.request.doctor.DoctorRequestDto;
import ua.com.alevel.web.dto.response.PageData;
import ua.com.alevel.web.dto.response.doctor.DoctorResponseDto;

public interface DoctorFacade extends CrudFacade<DoctorRequestDto, DoctorResponseDto> {
    DoctorResponseDto findByUserId(Long id);

    PageData<DoctorResponseDto> findNotDeleted(WebRequest request);
}
