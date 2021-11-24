package ua.com.alevel.console.annotation.impl;

import org.reflections.Reflections;

import java.io.BufferedReader;
import java.lang.reflect.Method;
import java.util.Set;

public class DBInitter {

    public static void dbInitteralize(Class clazz) {
        try {
            Reflections reflections = new Reflections("ua.com.alevel");
            Set<Class<? extends Object>> allClasses =
                    reflections.getSubTypesOf(clazz);
            for (Class cass :
                    allClasses) {
                if (!cass.isInterface()) {
                    Method method = cass.getMethod("initDB");
                    method.invoke(cass.getConstructor().newInstance());
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
