package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.facade.RegistrationFacade;
import ua.com.alevel.persistence.entity.user.User;
import ua.com.alevel.persistence.type.RoleType;
import ua.com.alevel.service.UserCrudService;
import ua.com.alevel.web.dto.request.register.AuthDto;

@Service
public class RegistrationFacadeImpl implements RegistrationFacade {

    private final UserCrudService personalService;

    public RegistrationFacadeImpl(UserCrudService personalService) {
        this.personalService = personalService;
    }

    @Override
    public void registration(AuthDto dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        if (dto.getEmail().contains("admin")) {
            user.setRoleType(RoleType.ROLE_ADMIN);
        } else {
            user.setRoleType(RoleType.ROLE_PERSONAL);
        }
        personalService.create(user);
    }
}
