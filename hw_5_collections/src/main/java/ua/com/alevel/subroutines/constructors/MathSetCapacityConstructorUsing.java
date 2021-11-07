package ua.com.alevel.subroutines.constructors;

import ua.com.alevel.MathSet;
import ua.com.alevel.MathSetUsabilityMain;
import ua.com.alevel.console.ConsoleSubroutine;

import java.io.BufferedReader;

public class MathSetCapacityConstructorUsing extends ConsoleSubroutine {

    public final String LONG_DESCRIPTION = "Создает обьект MathSet используя конструктор принимающий целочисленное значение мощности.";
    public final String SHORT_DESCRIPTION = "Конструктор с мощностью";

    public final String EXPECTED_INPUT = "Целое число мощности множества";

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
            MathSetUsabilityMain.mathSet = new MathSet(Integer.parseInt(str));
            System.out.println("Создался конструктор c заданой мощностью, стерегитесь его, он агрессивно настроен\n" + MathSetUsabilityMain.mathSet);
        } catch (Exception e) {
            System.err.println(ERROR_MESSAGE);
            System.out.println(EXPECTED_INPUT);
        }
    }
}
