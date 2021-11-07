package ua.com.alevel.subroutines.methods;

import ua.com.alevel.MathSet;
import ua.com.alevel.MathSetHelper;
import ua.com.alevel.MathSetUsabilityMain;
import ua.com.alevel.console.ConsoleSubroutine;

import java.io.BufferedReader;

public class MathSetInterMathSet extends ConsoleSubroutine {
    public final String LONG_DESCRIPTION = "Пересекает два множества. Текущее и новое";
    public final String SHORT_DESCRIPTION = "Пересечение с множеством";

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
        System.out.println("Первое множество:\n" + MathSetUsabilityMain.mathSet);
        MathSet mathSet = new MathSet(MathSetHelper.generateRandomNumbers());
        System.out.println("Второе множество:\n" + mathSet);
        MathSetUsabilityMain.mathSet.intersection(mathSet);
        System.out.println("Новое множество:\n" + MathSetUsabilityMain.mathSet.toString());
    }
}
