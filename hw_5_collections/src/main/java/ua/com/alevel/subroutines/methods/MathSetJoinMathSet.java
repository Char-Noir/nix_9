package ua.com.alevel.subroutines.methods;

import ua.com.alevel.MathSet;
import ua.com.alevel.MathSetHelper;
import ua.com.alevel.MathSetUsabilityMain;
import ua.com.alevel.console.ConsoleSubroutine;

import java.io.BufferedReader;

public class MathSetJoinMathSet extends ConsoleSubroutine {
    public final String LONG_DESCRIPTION = "Добавляет множество чисел в множество.";
    public final String SHORT_DESCRIPTION = "Добавляет множество";

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
        System.out.println("Старое множество:\n" + MathSetUsabilityMain.mathSet);
        MathSetUsabilityMain.mathSet.join(new MathSet(MathSetHelper.generateRandomNumbers()));
        System.out.println("Новое множество:\n" + MathSetUsabilityMain.mathSet.toString());
    }
}
