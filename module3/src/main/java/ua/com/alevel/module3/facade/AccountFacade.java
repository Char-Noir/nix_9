package ua.com.alevel.module3.facade;

import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.module3.dto.AccountRequestDto;
import ua.com.alevel.module3.dto.AccountResponseDto;
import ua.com.alevel.module3.dto.ClientResponceDto;
import ua.com.alevel.module3.view.PageData;

public interface AccountFacade extends BaseFacade<AccountRequestDto, AccountResponseDto> {
    PageData<AccountResponseDto> findAllByClient(WebRequest webRequest, Long id);
}
