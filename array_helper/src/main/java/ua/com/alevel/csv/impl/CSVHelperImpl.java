package ua.com.alevel.csv.impl;

import ua.com.alevel.csv.CSVHelper;
import ua.com.alevel.csv.SerializableEnum;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class CSVHelperImpl<Entity> implements CSVHelper<Entity> {

    private static final char separator = ',';

    private final Class<Entity> type;

    public CSVHelperImpl(Class<Entity> type) {
        this.type = type;
    }

    public Class<Entity> getMyType() {
        return this.type;
    }

    @Override
    public String getHeader() {
        Field[] fields = (getMyType().getDeclaredFields());
        List<Field> allFields = new ArrayList<>(Arrays.asList(fields));
        Class subclass;
        Class superclass = getMyType().getSuperclass();
        while (superclass != null) {
            fields = (superclass.getDeclaredFields());
            allFields.addAll(Arrays.asList(fields));
            subclass = superclass;
            superclass = subclass.getSuperclass();
        }
        allFields.sort(Comparator.comparing(Field::getName));
        StringBuilder sb = new StringBuilder();
        for (Field field : allFields) {
            sb.append(field.getName());
            sb.append(separator);
        }
        return sb.deleteCharAt(sb.length() - 1).append('\n').toString();
    }

    @Override
    public String objectsToString(Entity entity) throws IllegalAccessException {
        Field[] fields = (getMyType().getDeclaredFields());
        List<Field> allFields = new ArrayList<>(Arrays.asList(fields));
        Class subclass;
        Class superclass = getMyType().getSuperclass();
        while (superclass != null) {
            fields = (superclass.getDeclaredFields());
            allFields.addAll(Arrays.asList(fields));
            subclass = superclass;
            superclass = subclass.getSuperclass();
        }
        allFields.sort(Comparator.comparing(Field::getName));
        StringBuilder sb = new StringBuilder();
        for (Field field : allFields) {
            field.setAccessible(true);
            if (SerializableEnum.class.isAssignableFrom(field.getType())) {
                sb.append(((Enum) field.get(entity)).name());
            } else {
                sb.append(field.get(entity).toString());
            }
            field.setAccessible(false);
            sb.append(separator);
        }
        return sb.deleteCharAt(sb.length() - 1).append('\n').toString();
    }

    @Override
    public Entity stringToObject(String[] headers, String string) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Entity entity = getMyType().getConstructor().newInstance();
        String[] fields = string.split(String.valueOf(separator));
        Field[] fields1 = getMyType().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            String field = fields[i];
            String name = headers[i];
            Field nameField;
            try {
                nameField = getMyType().getDeclaredField(name);
            } catch (Exception e) {
                nameField = getMyType().getSuperclass().getDeclaredField(name);
            }
            nameField.setAccessible(true);
            if (String.class.equals(nameField.getType())) {
                nameField.set(entity, field);
            } else if (Long.class.equals(nameField.getType())) {
                Long l = Long.valueOf(field);
                nameField.set(entity, l);
            } else if (Double.class.equals(nameField.getType())) {
                Double d = Double.valueOf(field);
                nameField.set(entity, d);
            } else if (LocalDate.class.equals(nameField.getType())) {
                LocalDate localDate = LocalDate.parse(field);
                nameField.set(entity, localDate);
            }
            else if (LocalTime.class.equals(nameField.getType())) {
                LocalTime localTime = LocalTime.parse(field);
                nameField.set(entity, localTime);
            }else if (SerializableEnum.class.isAssignableFrom(nameField.getType())) {
                Class<? extends Enum> clazz = (Class<? extends Enum>) nameField.getType();
                Enum enam = Enum.valueOf(clazz, field);
                nameField.set(entity, enam);
            } else {
                throw new RuntimeException("This field type does not supported");
            }
        }
        return entity;
    }
}
