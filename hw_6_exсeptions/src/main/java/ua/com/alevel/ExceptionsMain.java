package ua.com.alevel;

import ua.com.alevel.console.ConsoleHelper;
import ua.com.alevel.console.ConsoleSubroutine;
import ua.com.alevel.subroutines.*;

import java.util.ArrayList;
import java.util.List;

public class ExceptionsMain {

    public static String outputFormat = "dd/MM/yy hh:mm:ss:SSS";
    public static String inputFormat = "dd/MM/yy hh:mm:ss:SSS";

    public static void main(String[] args) {
        System.out.println("Пожалуйста, если вы не хотите указывать некий параметр, но он присутствует в форматной строке, то оставьте его пустым, но оставьте все разделительные знаки. Это оставит строгость ввода данных");
        List<ConsoleSubroutine> list = new ArrayList<>();
        list.add(new DateDifferencesSubroutine());
        list.add(new DateAddTime());
        list.add(new DateSubTime());
        list.add(new SetInputDateFormat());
        list.add(new SetOutputDateFormat());
        ConsoleHelper.runConsoleApplication(list);
    }
}
