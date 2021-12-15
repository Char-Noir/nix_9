package ua.com.alevel.subroutines;

import ua.com.alevel.MoralDate;
import ua.com.alevel.console.ConsoleSubroutine;
import ua.com.alevel.impl.MoralDateImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Locale;

public class DateOutputer extends ConsoleSubroutine {

    public final String LONG_DESCRIPTION = "Позволяет отформатировать дату из одного формата в формат подряд";
    public final String SHORT_DESCRIPTION = "Форматирование дат";

    public final String EXPECTED_INPUT = "Форматная строка ввода и сама дата";

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
        try {
            String q;
            do {
                System.out.println("Введите формат даты!");
                String format = bufferedReader.readLine();
                String date1 = bufferedReader.readLine();
                MoralDate m1;
                m1 = new MoralDateImpl(date1, format);
                System.out.println(m1.toString("ddMMyyyy"));
                System.out.println("Если хотите выйти напишите q");
                q = bufferedReader.readLine();
            } while (!q.startsWith("q"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
