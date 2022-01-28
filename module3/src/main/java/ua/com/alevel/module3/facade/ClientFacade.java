package ua.com.alevel.module3.facade;

import ua.com.alevel.module3.dto.ClientRequestDto;
import ua.com.alevel.module3.dto.ClientResponceDto;

public interface ClientFacade extends BaseFacade<ClientRequestDto, ClientResponceDto> {

    ClientResponceDto findById(Long id);

    boolean exists(Long id);
}
