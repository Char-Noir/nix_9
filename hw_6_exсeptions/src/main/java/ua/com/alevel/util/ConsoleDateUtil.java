package ua.com.alevel.util;

import ua.com.alevel.enums.DateComponent;
import ua.com.alevel.enums.DateFormatEnum;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ConsoleDateUtil {

    private static final Map<String, String> FORMATS = new HashMap<>();
    private static final Map<Integer, String> DEFAULT_FORMATS = new HashMap<>();

    static {
        FORMATS.put("yy", "Ввод года двумя цифрами");
        FORMATS.put("yyyy", "Ввод года четырьмя цифрами");
        FORMATS.put("MM", "Ввод месяца двума цифрами");
        FORMATS.put("MMM", "Ввод месяца строкой");
        FORMATS.put("dd", "Ввод дня двумя цифрами");
        FORMATS.put("hh", "Ввод часов двумя цифрами");
        FORMATS.put("mm", "Ввод минут двумя цифрами");
        FORMATS.put("ss", "Ввод секунд двумя цифрами");
        FORMATS.put("SSS", "Ввод милисекунд трёмя цифрами");
        DEFAULT_FORMATS.put(1, "dd/MM/yy hh:mm:ss:SSS");
        DEFAULT_FORMATS.put(2, "dd/MM/yyyy hh:mm:ss:SSS");
        DEFAULT_FORMATS.put(3, "dd/MMM/yyyy hh:mm:ss:SSS");
        DEFAULT_FORMATS.put(4, "dd/MMM/yy hh:mm:ss:SSS");
        DEFAULT_FORMATS.put(5, "MMM-dd-yy hh:mm:ss:SSS");
        DEFAULT_FORMATS.put(6, "MM-dd-yy hh:mm:ss:SSS");
        DEFAULT_FORMATS.put(7, "MMM-dd-yyyy hh:mm:ss:SSS");
        DEFAULT_FORMATS.put(8, "MM-dd-yyyy hh:mm:ss:SSS");
        DEFAULT_FORMATS.put(9, "dd-MMM-yyyy hh:mm:ss:SSS");
        DEFAULT_FORMATS.put(10, "dd-MM-yyyy hh:mm:ss:SSS");
        DEFAULT_FORMATS.put(11, "dd-MMM-yy hh:mm:ss:SSS");
        DEFAULT_FORMATS.put(12, "dd-MM-yy hh:mm:ss:SSS");
    }

    public static DateComponent readDateComponent(BufferedReader br) {
        DateComponent[] components = DateComponent.values();
        System.out.println("Выберите компонет из списка");
        for (DateComponent component : components) {
            System.out.println(component.name() + " or short:" + component.getShortName());
        }
        try {
            String component = br.readLine();
            return DateComponent.getComponentByName(component);
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No such component");
    }

    public static DateFormatEnum readDateFormat(BufferedReader bufferedReader) {
        DateFormatEnum[] components = DateFormatEnum.values();
        System.out.println("Выберите формат из списка");
        for (DateFormatEnum component : components) {
            System.out.println(component.name() + " or short:" + component.getShortName());
        }
        try {
            String component = bufferedReader.readLine();
            return DateFormatEnum.getFormatByName(component);
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No such format");
    }

    public static String readStringFormat(BufferedReader bufferedReader) {
        System.out.println("Доступные форматы для строки");
        try {
            for (Integer name :
                    DEFAULT_FORMATS.keySet()) {
                System.out.println(name + ") " + DEFAULT_FORMATS.get(name));
            }
            System.out.println("Если хотите ввести свою форматную строку, то введите что угодно кроме цифр выше");
            String answer = bufferedReader.readLine().trim();
            int choose = 0;
            if (Character.isDigit(answer.charAt(0))) {
                choose = Integer.parseInt(answer);
            }
            if (choose < 1 || choose > DEFAULT_FORMATS.size()) {
                System.out.println("Возможные форматные патерны, которые можно использовать для своих форматных строк");
                for (String name : FORMATS.keySet()) {
                    System.out.println(name + " : " + FORMATS.get(name));
                }
                return bufferedReader.readLine();
            }
            return DEFAULT_FORMATS.get(choose);
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Wrong format");
    }
}
