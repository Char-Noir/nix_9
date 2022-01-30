package ua.com.alevel.facade;

import ua.com.alevel.web.dto.request.user.UserRequestDto;
import ua.com.alevel.web.dto.response.user.UserResponseDto;

public interface UserFacade extends CrudFacade<UserRequestDto, UserResponseDto> {
    UserResponseDto findByEmail(String email);

}
