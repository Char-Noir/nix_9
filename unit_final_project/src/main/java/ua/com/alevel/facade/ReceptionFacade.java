package ua.com.alevel.facade;

import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.web.dto.request.reception.ReceptionRequestDto;
import ua.com.alevel.web.dto.response.PageData;
import ua.com.alevel.web.dto.response.reception.ReceptionResponseDto;

public interface ReceptionFacade extends CrudFacade<ReceptionRequestDto, ReceptionResponseDto> {
    PageData<ReceptionResponseDto> findAllByPatient(WebRequest request, Long id);

    PageData<ReceptionResponseDto> findAllByDoctor(WebRequest request, Long id);
}
