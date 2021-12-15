package ua.com.alevel.subroutines;

import ua.com.alevel.ExceptionsMain;
import ua.com.alevel.console.ConsoleSubroutine;
import ua.com.alevel.util.ConsoleDateUtil;

import java.io.BufferedReader;

public class SetOutputDateFormat extends ConsoleSubroutine {
    public final String LONG_DESCRIPTION = "Позволяет изменить формат вывода даты";
    public final String SHORT_DESCRIPTION = "Формат вывода";

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
        ExceptionsMain.outputFormat = ConsoleDateUtil.readStringFormat(bufferedReader);
    }
}
