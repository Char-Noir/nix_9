package ua.com.alevel.subroutines.constructors;

import ua.com.alevel.MathSet;
import ua.com.alevel.MathSetUsabilityMain;
import ua.com.alevel.console.ConsoleSubroutine;

import java.io.BufferedReader;

public class MathSetDefaultConstructorUsing extends ConsoleSubroutine {
    public final String LONG_DESCRIPTION = "Создает обьект MathSet используя конструктор по умолчанию.";
    public final String SHORT_DESCRIPTION = "Конструкторы\nКонструктор по умолчанию";

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
        MathSetUsabilityMain.mathSet = new MathSet();
        System.out.println("Создался дефолтный конструктор, стерегитесь его, он агрессивно настроен.\n" + MathSetUsabilityMain.mathSet);
    }
}
