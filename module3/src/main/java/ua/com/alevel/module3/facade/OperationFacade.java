package ua.com.alevel.module3.facade;

import ua.com.alevel.module3.dto.OperationRequestDto;
import ua.com.alevel.module3.dto.OperationResponseDto;
import ua.com.alevel.module3.view.PageData;

public interface OperationFacade extends BaseFacade<OperationRequestDto, OperationResponseDto> {

    void create(OperationRequestDto req);

    void update(OperationRequestDto req, Long id);

    void delete(Long id);

    PageData<OperationResponseDto> findAllByAccount(Long id);
}
