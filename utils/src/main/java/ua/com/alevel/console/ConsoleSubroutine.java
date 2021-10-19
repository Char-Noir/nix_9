package ua.com.alevel.console;

import java.io.BufferedReader;

public abstract class ConsoleSubroutine {
    protected final String ERROR_MESSAGE = "Неверно введенный данные.";
    protected final String EXPECTED_INPUT = "Строка";
    private final String SHORT_DESCRIPTION = "Краткое описание отсутствует";
    private final String LONG_DESCRIPTION = "Полное описание отсутствует";

    public abstract void run(BufferedReader bufferedReader);

    public String getShortDescription() {
        return SHORT_DESCRIPTION;
    }

    public String getLongDescription() {
        return LONG_DESCRIPTION;
    }

    public String getExpectedInput() {
        return EXPECTED_INPUT;
    }
}
