package ua.com.alevel.web.dto.request.user;

import ua.com.alevel.persistence.converters.UserRoleConverter;
import ua.com.alevel.persistence.entity.user.User;
import ua.com.alevel.web.dto.request.RequestDto;

public class UserRequestDto extends RequestDto {

    private String password;
    private String email;
    private String role = "user";

    public String getPassword() {
        return password;
    }

    public UserRequestDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRequestDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getRole() {
        return role;
    }

    public UserRequestDto setRole(String role) {
        this.role = role;
        return this;
    }

    public User getUser() {
        User user = new User();
        user.setRoleType(UserRoleConverter.convertStringToRoleType(role));
        user.setPassword(password);
        user.setEmail(email);
        return user;
    }

}
