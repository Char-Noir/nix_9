package ua.com.alevel.hw_8_web_jdbc.facade;

import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.hw_8_web_jdbc.dto.impl.reception.ReceptionFullResponseDto;
import ua.com.alevel.hw_8_web_jdbc.dto.impl.reception.ReceptionRequestDto;
import ua.com.alevel.hw_8_web_jdbc.dto.impl.reception.ReceptionResponseDto;
import ua.com.alevel.hw_8_web_jdbc.view.PageData;

public interface ReceptionFacade extends BaseFacade<ReceptionRequestDto, ReceptionResponseDto, ReceptionFullResponseDto> {
    PageData<ReceptionResponseDto> findAllByPatientId(WebRequest webRequest, Long id);

    PageData<ReceptionResponseDto> findAllByDoctorId(WebRequest webRequest, Long id);
}
