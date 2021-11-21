package ua.com.alevel.dao;

import ua.com.alevel.entity.User;
import ua.com.alevel.implementations.DoubleLinkedListImpl;

import java.util.UUID;

public class UserDao implements BaseEntityDao<User> {

    private final DoubleLinkedListImpl<User> users;
    private static UserDao instance;

    private UserDao() {
        users = new DoubleLinkedListImpl<>(User.class);
        create(new User(15,"Ann"));
        create(new User(18,"Ehor"));
        create(new User(56,"Danyil"));
    }

    public static UserDao getInstance() {
        if (instance == null) {
            instance = new UserDao();
        }
        return instance;
    }

    public void create(User user) {
        user.setId(generateId());
        users.add(user);
    }

    public boolean update(User user) {
        return users.update(user);
    }

    public boolean delete(long id) {
        return users.remove(id);
    }

    public User findById(long id) {
        User user = users.get(id);
        if(user==null){
            throw new RuntimeException("User not found");
        }
        else{
            return user;
        }
    }

    public User[] findAll() {
        return users.toArray();
    }

    private long generateId() {
        long newId;
        do{
        newId = idNext();
        }while (users.contains(newId));
        return newId;
    }

    protected long idNext(){
        return UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;//generate positive random long values
    }
}
