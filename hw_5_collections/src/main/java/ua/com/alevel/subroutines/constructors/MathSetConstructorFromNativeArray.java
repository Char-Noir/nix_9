package ua.com.alevel.subroutines.constructors;

import ua.com.alevel.MathSet;
import ua.com.alevel.MathSetHelper;
import ua.com.alevel.MathSetUsabilityMain;
import ua.com.alevel.console.ConsoleSubroutine;

import java.io.BufferedReader;

public class MathSetConstructorFromNativeArray extends ConsoleSubroutine {
    public final String LONG_DESCRIPTION = "Создает обьект MathSet используя конструктор принимающий массив чисел";
    public final String SHORT_DESCRIPTION = "Конструктор с неявным числовым масивом";

    public final String EXPECTED_INPUT = "Набор чисел через \" \"(пробел). Ввод вне правил будет принят как разрешение на создание рандомного массива";

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
        MathSetUsabilityMain.mathSet = new MathSet((MathSetHelper.generateRandomNumbers()), (MathSetHelper.generateRandomNumbers()), (MathSetHelper.generateRandomNumbers()));
        System.out.println("Создался конструктор на основе нативного масива масивов чисел, стерегитесь его, он агрессивно настроен\n" + MathSetUsabilityMain.mathSet);

    }
}
