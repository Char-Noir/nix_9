package ua.com.alevel.subroutines.constructors;

import ua.com.alevel.MathSet;
import ua.com.alevel.MathSetHelper;
import ua.com.alevel.MathSetUsabilityMain;
import ua.com.alevel.console.ConsoleSubroutine;

import java.io.BufferedReader;
import java.util.Arrays;

public class MathSetConstructorFromMathSetArray extends ConsoleSubroutine {
    public final String LONG_DESCRIPTION = "Создает обьект MathSet используя конструктор принимающий массив других MathSet-ов";
    public final String SHORT_DESCRIPTION = "Конструктор с MathSet масивом";

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
        MathSet[] mathSet = new MathSet[]{new MathSet(MathSetHelper.generateRandomNumbers()), new MathSet(MathSetHelper.generateRandomNumbers()), new MathSet(MathSetHelper.generateRandomNumbers())};
        System.out.println("Изначальные MathSet-ы:\n" + Arrays.toString(mathSet));
        MathSetUsabilityMain.mathSet = new MathSet(new MathSet(MathSetHelper.generateRandomNumbers()), new MathSet(MathSetHelper.generateRandomNumbers()), new MathSet(MathSetHelper.generateRandomNumbers()));
        System.out.println("Создался конструктор на основе другого множества, стерегитесь его, он агрессивно настроен\n" + MathSetUsabilityMain.mathSet);
    }
}
