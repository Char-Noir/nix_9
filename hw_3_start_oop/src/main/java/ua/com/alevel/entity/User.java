package ua.com.alevel.entity;

import ua.com.alevel.logic.entity.BaseEntity;

public class User extends BaseEntity {

    private String name;
    private int age;

    public User(int age, String name) {
        super();
        this.setAge(age);
        this.setName(name);
    }

    public User(long id, int age, String name) {
        super(id);
        setName(name);
        setAge(age);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name==null){
            throw new NullPointerException("Name is null");
        }
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if(age<0){
            throw new IllegalArgumentException("Age must be bigger than or equals 0");
        }
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}'+'\n';
    }
}