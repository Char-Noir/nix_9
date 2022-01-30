package ua.com.alevel.web.dto.response.user;

import ua.com.alevel.persistence.converters.UserRoleConverter;
import ua.com.alevel.persistence.entity.user.User;
import ua.com.alevel.web.dto.response.ResponseDto;

public class UserResponseDto extends ResponseDto {

    private String password;
    private String email;
    private String role;

    public UserResponseDto(User user) {
        super(user.getId());
        System.out.println(user);
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.role = UserRoleConverter.convertRoleTypeToString(user.getRoleType());
        this.setVisible(user.getEnabled());
    }

    public String getPassword() {
        return password;
    }

    public UserResponseDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserResponseDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getRole() {
        return role;
    }

    public UserResponseDto setRole(String role) {
        this.role = role;
        return this;
    }

    @Override
    public String toString() {
        return "UserResponseDto{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
