package ua.com.alevel.subroutines.constructors;

import ua.com.alevel.MathSet;
import ua.com.alevel.MathSetHelper;
import ua.com.alevel.MathSetUsabilityMain;
import ua.com.alevel.console.ConsoleSubroutine;

import java.io.BufferedReader;

public class MathSetConstructorFromAnotherMathSet extends ConsoleSubroutine {
    public final String LONG_DESCRIPTION = "Создает обьект MathSet используя конструктор принимающий другой MathSet";
    public final String SHORT_DESCRIPTION = "Конструктор с другим  MathSet";

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
        MathSet mathSet = new MathSet(MathSetHelper.generateRandomNumbers());
        System.out.println("Изначальный MathSet:\n" + mathSet);
        MathSetUsabilityMain.mathSet = new MathSet(mathSet);
        System.out.println("Создался конструктор на основе другого множества, стерегитесь его, он агрессивно настроен\n" + MathSetUsabilityMain.mathSet);
    }
}
