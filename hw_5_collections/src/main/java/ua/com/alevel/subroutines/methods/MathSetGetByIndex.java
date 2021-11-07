package ua.com.alevel.subroutines.methods;

import ua.com.alevel.MathSetUsabilityMain;
import ua.com.alevel.console.ConsoleSubroutine;

import java.io.BufferedReader;

public class MathSetGetByIndex extends ConsoleSubroutine {
    public final String LONG_DESCRIPTION = "Выводит значение по индексу";
    public final String SHORT_DESCRIPTION = "Получить число";

    public final String EXPECTED_INPUT = "Целочисленный индекс";

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
            int index = (Integer.parseInt(str));
            Number number = MathSetUsabilityMain.mathSet.get(index);
            System.out.println("Полученое число:" + number.toString());
        } catch (Exception e) {
            System.err.println(ERROR_MESSAGE);
            System.out.println(EXPECTED_INPUT);
        }
    }
}
