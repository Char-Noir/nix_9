package ua.com.alevel.subroutines.methods;

import ua.com.alevel.MathSetUsabilityMain;
import ua.com.alevel.console.ConsoleSubroutine;

import java.io.BufferedReader;

public class MathSetSortDescIndex extends ConsoleSubroutine {
    public final String LONG_DESCRIPTION = "Сортирует вниз множество по индексу начала и конца";
    public final String SHORT_DESCRIPTION = "Низхлодящая сортировка по индексам";

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
            MathSetUsabilityMain.mathSet.sortDesc(start, end);
            System.out.println("Новое множество:\n" + MathSetUsabilityMain.mathSet.toString());
        } catch (Exception e) {
            System.err.println(ERROR_MESSAGE);
            System.out.println(EXPECTED_INPUT);
        }
    }
}
