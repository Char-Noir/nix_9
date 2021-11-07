package ua.com.alevel.subroutines.methods;

import ua.com.alevel.MathSetHelper;
import ua.com.alevel.MathSetUsabilityMain;
import ua.com.alevel.console.ConsoleSubroutine;

import java.io.BufferedReader;

public class MathSetAddNumber extends ConsoleSubroutine {
    public final String LONG_DESCRIPTION = "Добавляет число в множество если влазит. Принимает одно число";
    public final String SHORT_DESCRIPTION = "Добавляет число в множество";

    public final String EXPECTED_INPUT = "Любое число";

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
            System.out.println("Ваше число:" + number);
            MathSetUsabilityMain.mathSet.add(number);
            System.out.println("Новое множество:\n" + MathSetUsabilityMain.mathSet.toString());
        } catch (Exception e) {
            System.err.println(ERROR_MESSAGE);
            System.out.println(EXPECTED_INPUT);
        }
    }
}
