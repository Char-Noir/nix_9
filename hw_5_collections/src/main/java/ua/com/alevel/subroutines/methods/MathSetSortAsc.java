package ua.com.alevel.subroutines.methods;

import ua.com.alevel.MathSetUsabilityMain;

import java.io.BufferedReader;

public class MathSetSortAsc extends ua.com.alevel.console.ConsoleSubroutine {
    public final String LONG_DESCRIPTION = "Сортирует числа в множестве в восходящем порядке";
    public final String SHORT_DESCRIPTION = "Восходящая сортировка";

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
        MathSetUsabilityMain.mathSet.sortAsc();
        System.out.println("Новое множество:\n" + MathSetUsabilityMain.mathSet.toString());
    }
}
