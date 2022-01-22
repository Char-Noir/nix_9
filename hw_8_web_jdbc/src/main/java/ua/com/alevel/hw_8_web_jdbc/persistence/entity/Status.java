package ua.com.alevel.hw_8_web_jdbc.persistence.entity;

import java.util.Locale;

public enum Status {

    Doctor,
    Patient,
    Admin,
    Deleted;


    public static Status getStatusByName(String str){
       str = str.toLowerCase(Locale.ROOT);
        for (Status status : Status.values()) {
            if(status.name().toLowerCase(Locale.ROOT).equals(str)){
                return status;
            }
        }
        return  null;
    }

    public boolean isDeleted(){
        return this == Deleted;
    }

    public boolean isAdmin(){
        return this == Admin;
    }
}
