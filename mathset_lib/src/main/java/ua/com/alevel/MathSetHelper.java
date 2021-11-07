package ua.com.alevel;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MathSetHelper {
    public static Number[] generateRandomNumbers() {
        Random r = new Random();
        int iterator = r.nextInt(50);
        Number[] numbers2 = new Number[iterator];
        for (int i = 0; i < iterator; i++) {
            int choice = r.nextInt(3);
            switch (choice) {
                case 0 -> numbers2[i] = (r.nextInt(1000));
                case 1 -> numbers2[i] = (r.nextDouble(150));
                case 2 -> numbers2[i] = (r.nextFloat(75));
                case 3 -> numbers2[i] = (r.nextLong());
                case 4 -> numbers2[i] = (BigDecimal.valueOf(r.nextDouble()));
                case 5 -> numbers2[i] = (new BigInteger(String.valueOf(r.nextInt())));
            }
        }
        return numbers2;
    }

    public static Number[] stringToNumberArray(String str) throws ParseException {
        str = str.strip();
        str = str.replace('.', ',');
        String[] strings = str.split(" ");
        List<String> list = new LinkedList<>();
        for (String sir : strings) {
            String ser = sir.trim();
            if (!ser.strip().equals("")) {
                list.add(ser);
            }
        }
        strings = list.toArray(new String[0]);
        Number[] numbers2 = new Number[strings.length];
        for (int i = 0; i < strings.length; i++) {
            System.out.println(strings[i]);
            numbers2[i] = (NumberFormat.getInstance().parse(strings[i]));
        }
        return numbers2;
    }

    public static Number stringToNumber(String str) throws ParseException {
        str = str.strip();
        str = str.replace('.', ',');
        return NumberFormat.getInstance().parse(str);
    }
}
