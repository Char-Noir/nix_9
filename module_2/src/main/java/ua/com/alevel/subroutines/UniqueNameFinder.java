package ua.com.alevel.subroutines;

import ua.com.alevel.MoralDate;
import ua.com.alevel.console.ConsoleSubroutine;
import ua.com.alevel.impl.MoralDateImpl;
import ua.com.alevel.util.ArrayHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class UniqueNameFinder extends ConsoleSubroutine {

    public final String LONG_DESCRIPTION = "Находит первое уникальное имя в масиве имен. ";
    public final String SHORT_DESCRIPTION = "Выводит первое уникальное имя";
    public final String EXPECTED_INPUT = "Строка, внутри которой каждое имя разделено пробелом.";

    @Override
    public String getShortDescription() {
        return SHORT_DESCRIPTION;
    }

    @Override
    public String getLongDescription() {
        return LONG_DESCRIPTION;
    }

    @Override
    public String getExpectedInput() {
        return EXPECTED_INPUT;
    }

    @Override
    public void run(BufferedReader bufferedReader) {
        List<String> ints = new LinkedList<>();
        String uniques = null;
        try {
            String line = bufferedReader.readLine();
            String[] inputs = line.split(" ");
            ints.addAll(Arrays.asList(inputs));
            uniques = ArrayHelper.findFirstUnique(ints);
        } catch (Exception e) {
            System.err.println(ERROR_MESSAGE);
            System.out.println(EXPECTED_INPUT);
        }
        System.out.println(new StringBuilder("Результат задания: ").append("Для такого масива имен").append('\n').append(ints).append("\nБыло найдено такое уникальное имя:  ").append((uniques == null) ? " Никакое" : uniques));
    }
}
