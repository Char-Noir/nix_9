package ua.com.alevel.subroutines;

import ua.com.alevel.ExceptionsMain;
import ua.com.alevel.MoralDate;
import ua.com.alevel.console.ConsoleSubroutine;
import ua.com.alevel.enums.DateFormatEnum;
import ua.com.alevel.impl.MoralDateImpl;
import ua.com.alevel.util.ConsoleDateUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Locale;

public class DateDifferencesSubroutine extends ConsoleSubroutine {

    public final String LONG_DESCRIPTION = "Позволяет узнать разницу между датами в нужной вам единице";
    public final String SHORT_DESCRIPTION = "Разница дат";

    public final String EXPECTED_INPUT = "";

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
            System.out.println("Введите первую дату!");
            System.out.println("Формат:");
            System.out.println(ExceptionsMain.inputFormat);
            String date1 = bufferedReader.readLine();
            MoralDate m1;
            m1 = new MoralDateImpl(date1, ExceptionsMain.inputFormat);
            System.out.println(m1.toString(ExceptionsMain.outputFormat));
            System.out.println("Введите вторую дату!");
            System.out.println("Формат:");
            System.out.println(ExceptionsMain.inputFormat);
            String date2 = bufferedReader.readLine();
            MoralDate m2;
            m2 = new MoralDateImpl(date2, ExceptionsMain.inputFormat);
            System.out.println(m2.toString(ExceptionsMain.outputFormat));
            DateFormatEnum comp = ConsoleDateUtil.readDateFormat(bufferedReader);
            Long diff = m1.diff(m2, comp);
            System.out.println("Разница составила " + diff + " " + comp.name().toLowerCase(Locale.ROOT));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
