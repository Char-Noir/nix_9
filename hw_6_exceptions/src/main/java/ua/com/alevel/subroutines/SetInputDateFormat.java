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

public class SetInputDateFormat extends ConsoleSubroutine {
    public final String LONG_DESCRIPTION = "Позволяет изменить формат ввода даты";
    public final String SHORT_DESCRIPTION = "Формат Ввода";

    public final String EXPECTED_INPUT = "Новая строка формата";

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
        ExceptionsMain.inputFormat = ConsoleDateUtil.readStringFormat(bufferedReader);
    }
}
