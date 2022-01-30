package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.facade.UserFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.user.User;
import ua.com.alevel.service.UserCrudService;
import ua.com.alevel.util.WebUtil;
import ua.com.alevel.web.dto.request.user.UserRequestDto;
import ua.com.alevel.web.dto.response.PageData;
import ua.com.alevel.web.dto.response.user.UserResponseDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserFacadeImpl implements UserFacade {

    private final UserCrudService userService;

    public UserFacadeImpl(UserCrudService userService) {
        this.userService = userService;
    }

    @Override
    public void create(UserRequestDto userRequestDto) {
        userService.create(userRequestDto.getUser());
    }

    @Override
    public void update(UserRequestDto userRequestDto, Long id) {
        Optional<User> user1 = userService.findById(id);
        if (user1.isPresent()) {
            User user = user1.get();
            if (userRequestDto.getEmail() != null) {
                user.setEmail(userRequestDto.getEmail());
            }
            if (userRequestDto.getPassword() != null) {
                user.setPassword(userRequestDto.getPassword());
            }
            userService.update(user);
        }
        throw new EntityNotFoundException("There are no such user");
    }

    @Override
    public void delete(Long id) {
        userService.delete(id);
    }

    @Override
    public UserResponseDto findById(Long id) {
        Optional<User> user = userService.findById(id);
        if (user.isPresent()) {
            return new UserResponseDto(user.get());
        }
        throw new EntityNotFoundException("There are no such user");
    }

    @Override
    public PageData<UserResponseDto> findAll(WebRequest request) {
        DataTableRequest dataTableRequest = WebUtil.generateDataTableRequestByWebRequest(request);
        DataTableResponse<User> tableResponse = userService.findAll(dataTableRequest);
        List<UserResponseDto> items = tableResponse.getItems().stream().
                map(UserResponseDto::new).
                collect(Collectors.toList());

        PageData<UserResponseDto> pageData = (PageData<UserResponseDto>) WebUtil.initPageData(tableResponse);
        pageData.setItems(items);
        return pageData;
    }

    @Override
    public long count() {
        return userService.count();
    }

    @Override
    public UserResponseDto findByEmail(String email) {
        Optional<User> user = userService.findByUsername(email);
        if (user.isPresent()) {
            return new UserResponseDto(user.get());
        }
        throw new EntityNotFoundException("There are no such user");
    }
}
