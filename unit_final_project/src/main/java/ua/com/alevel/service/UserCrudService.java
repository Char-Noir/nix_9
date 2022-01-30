package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.user.User;

import java.util.Optional;

public interface UserCrudService extends BaseCrudService<User> {

    Optional<User> findByUsername(String username);

}
