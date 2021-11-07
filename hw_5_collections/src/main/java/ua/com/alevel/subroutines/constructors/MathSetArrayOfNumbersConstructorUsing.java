package ua.com.alevel.subroutines.constructors;

import ua.com.alevel.MathSet;
import ua.com.alevel.MathSetHelper;
import ua.com.alevel.MathSetUsabilityMain;
import ua.com.alevel.console.ConsoleSubroutine;

import java.io.BufferedReader;

import static ua.com.alevel.MathSetUsabilityMain.mathSet;

public class MathSetArrayOfNumbersConstructorUsing extends ConsoleSubroutine {

    public final String LONG_DESCRIPTION = "Создает обьект MathSet используя конструктор принимающий массив чисел";
    public final String SHORT_DESCRIPTION = "Конструктор с числовым массивом";

    public final String EXPECTED_INPUT = "Набор чисел через \" \"(пробел). Ввод вне правил будет принят как разрешение на создание рандомного MathSet";
    public final String ERROR_MESSAGE = "Мы вас не поняли. Будет создан случайный MathSet";

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
            String str = bufferedReader.readLine();
            mathSet = new MathSet(MathSetHelper.stringToNumberArray(str));
        } catch (Exception e) {
            System.out.println(ERROR_MESSAGE);
            MathSetUsabilityMain.mathSet = new MathSet(MathSetHelper.generateRandomNumbers());
        }
        System.out.println("Создался конструктор c числовым масивом, стерегитесь его, он агрессивно настроен\n" + MathSetUsabilityMain.mathSet);
    }
}
