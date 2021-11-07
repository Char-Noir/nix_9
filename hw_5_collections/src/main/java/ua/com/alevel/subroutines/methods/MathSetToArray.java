package ua.com.alevel.subroutines.methods;

import ua.com.alevel.MathSetUsabilityMain;
import ua.com.alevel.console.ConsoleSubroutine;

import java.io.BufferedReader;

public class MathSetToArray extends ConsoleSubroutine {
    public final String LONG_DESCRIPTION = "Возвращает масив";
    public final String SHORT_DESCRIPTION = "Масив";

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
        Number[] numbers = MathSetUsabilityMain.mathSet.toArray();
        System.out.println("Массив");
        for (Number n : numbers
        ) {
            System.out.println(n);
        }

    }
}
