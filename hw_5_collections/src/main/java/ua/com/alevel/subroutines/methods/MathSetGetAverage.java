package ua.com.alevel.subroutines.methods;

import ua.com.alevel.MathSetUsabilityMain;
import ua.com.alevel.console.ConsoleSubroutine;

import java.io.BufferedReader;

public class MathSetGetAverage extends ConsoleSubroutine {
    public final String LONG_DESCRIPTION = "Выдает среднее значение множества";
    public final String SHORT_DESCRIPTION = "Среднее значение";

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
        if (MathSetUsabilityMain.mathSet == null) {
            System.out.println("Для начала вызовете один из конструкторов");
            return;
        }
        Number number = MathSetUsabilityMain.mathSet.getAverage();
        System.out.println("Среднее значение:" + number.toString());
    }
}
