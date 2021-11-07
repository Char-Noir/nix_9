package ua.com.alevel.subroutines.methods;

import ua.com.alevel.MathSetUsabilityMain;
import ua.com.alevel.console.ConsoleSubroutine;

import java.io.BufferedReader;

public class MathSetClear extends ConsoleSubroutine {
    public final String LONG_DESCRIPTION = "Очищает множество";
    public final String SHORT_DESCRIPTION = "Очистка";

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
        MathSetUsabilityMain.mathSet.clear();
        System.out.println("Новое множество:\n" + MathSetUsabilityMain.mathSet.toString());
    }
}
