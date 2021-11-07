package ua.com.alevel.subroutines.methods;

import ua.com.alevel.MathSetUsabilityMain;
import ua.com.alevel.console.ConsoleSubroutine;

import java.io.BufferedReader;

public class MathSetToArrayIndex extends ConsoleSubroutine {
    public final String LONG_DESCRIPTION = "Возвращает масив по индексам";
    public final String SHORT_DESCRIPTION = "Масив по индексам";

    public final String EXPECTED_INPUT = "Два числа через \" \"";

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
            String[] strings = str.strip().split(" ");
            if (strings.length != 2) {
                return;
            }
            int start = Integer.parseInt(strings[0]);
            int end = Integer.parseInt(strings[1]);
            System.out.println("Старое множество:\n" + MathSetUsabilityMain.mathSet.toString());
            Number[] numbers = MathSetUsabilityMain.mathSet.toArray(start, end);
            System.out.println("Массив");
            for (Number n : numbers
            ) {
                System.out.println(n);
            }
        } catch (Exception e) {
            System.err.println(ERROR_MESSAGE);
            System.out.println(EXPECTED_INPUT);
        }
    }
}
