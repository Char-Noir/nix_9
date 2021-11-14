package ua.com.alevel.controller;

import ua.com.alevel.console.responce.Responce;
import ua.com.alevel.entity.User;
import ua.com.alevel.service.BaseEntityService;
import ua.com.alevel.service.UserService;

public class UserController implements BaseEntityController<User> {

    private static UserController instance = new UserController();
    private final BaseEntityService<User> service = UserService.getInstance();

    public static UserController getInstance() {
        if (instance == null) {
            instance = new UserController();
        }
        return instance;
    }

    @Override
    public Responce create(int age, String name) {
        User user;
        try {
            user = new User(age, name);
            service.create(user);
            return Responce.getRedirect(new StringBuilder("User created with name: ").append(user.getName()).append(" and with age: ").append(user.getAge()).append("\n").toString(), "ShowAll");
        } catch (Exception e) {
            return Responce.getError("Error when creating user", e.getMessage());
        }

    }

    @Override
    public Responce update(long id, int age, String name) {
        try {
            User user = new User(id, age, name);
            if (service.update(user)) {
                return Responce.getRedirect("User updated", "ShowAll");
            } else {
                return Responce.getError("User not updated", "User not found");
            }
        } catch (Exception e) {
            return Responce.getError("User not updated", e.getMessage());
        }
    }

    @Override
    public Responce delete(long id) {
        try {
            if (service.delete(id)) {
                return Responce.getRedirect("User deleted", "ShowAll");
            } else {
                return Responce.getError("User not deleted", "User not found");
            }
        } catch (Exception e) {
            return Responce.getError("User not deleted", e.getMessage());
        }
    }

    @Override
    public Responce findById(long id) {
        User user;
        try {
            user = service.findById(id);
            if (user != null) {
                return Responce.getOk(user.toString());
            } else {
                return Responce.getError("User not found", "User equals null");
            }
        } catch (Exception e) {
            return Responce.getError("User not found", e.getMessage());
        }
    }

    @Override
    public Responce findAll() {
        User[] users = service.findAll();
        if (users.length == 0) {
            return Responce.getOk("No users in app");
        }
        StringBuilder stringBuilder = new StringBuilder("Users:\n");
        for (User user :
                users) {
            stringBuilder.append(user);
        }
        return Responce.getOk(stringBuilder.toString());

    }


}
