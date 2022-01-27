package ua.com.alevel.hw_10_web_repository.facade;

import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.hw_10_web_repository.dto.impl.reception.ReceptionFullResponseDto;
import ua.com.alevel.hw_10_web_repository.dto.impl.reception.ReceptionRequestDto;
import ua.com.alevel.hw_10_web_repository.dto.impl.reception.ReceptionResponseDto;
import ua.com.alevel.hw_10_web_repository.view.PageData;

public interface ReceptionFacade extends BaseFacade<ReceptionRequestDto, ReceptionResponseDto, ReceptionFullResponseDto> {
    PageData<ReceptionResponseDto> findAllByPatientId(WebRequest webRequest, Long id);

    PageData<ReceptionResponseDto> findAllByDoctorId(WebRequest webRequest, Long id);
}
