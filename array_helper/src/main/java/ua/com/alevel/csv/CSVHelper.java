package ua.com.alevel.csv;

import java.lang.reflect.InvocationTargetException;

public interface CSVHelper<Entity> {

    String getHeader();

    String objectsToString(Entity entity) throws IllegalAccessException;

    Entity stringToObject(String[] headers, String string) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException;
}
