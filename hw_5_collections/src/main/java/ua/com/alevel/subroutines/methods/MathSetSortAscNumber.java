package ua.com.alevel.subroutines.methods;

import ua.com.alevel.MathSetHelper;
import ua.com.alevel.MathSetUsabilityMain;

import java.io.BufferedReader;

public class MathSetSortAscNumber extends ua.com.alevel.console.ConsoleSubroutine {
    public final String LONG_DESCRIPTION = "Сортирует числа в множестве в восходящем порядке по числу";
    public final String SHORT_DESCRIPTION = "Восходящая сортировка по числу";

    public final String EXPECTED_INPUT = "Входное число";

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
        try {
            String str = bufferedReader.readLine();
            Number number = MathSetHelper.stringToNumber(str);
            System.out.println("Старое множество:\n" + MathSetUsabilityMain.mathSet.toString());
            MathSetUsabilityMain.mathSet.sortAsc(number);
            System.out.println("Новое множество:\n" + MathSetUsabilityMain.mathSet.toString());
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(ERROR_MESSAGE);
            System.out.println(EXPECTED_INPUT);
        }
    }
}
