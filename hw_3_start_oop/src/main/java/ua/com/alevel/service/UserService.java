package ua.com.alevel.service;

import ua.com.alevel.dao.BaseEntityDao;
import ua.com.alevel.dao.UserDao;
import ua.com.alevel.entity.User;

public class UserService implements BaseEntityService<User> {

    private final BaseEntityDao<User> userDao = UserDao.getInstance();
    private static UserService instance = new UserService();

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public void create(User user) {
        userDao.create(user);
    }

    public boolean update(User user) {
        System.out.println(user);
        return userDao.update(user);
    }

    public boolean delete(long id) {
        return userDao.delete(id);
    }

    public User findById(long id) {
        return userDao.findById(id);
    }

    public User[] findAll() {
        return userDao.findAll();
    }
}