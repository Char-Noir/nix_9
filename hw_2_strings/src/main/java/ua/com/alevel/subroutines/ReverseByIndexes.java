package ua.com.alevel.subroutines;

import ua.com.alevel.ReverseString;
import ua.com.alevel.console.ConsoleSubroutine;

import java.io.BufferedReader;

public class ReverseByIndexes extends ConsoleSubroutine {

    public final String LONG_DESCRIPTION = "Возвращает строку, в которой реверсирована подстрока, указанная индексами начала и конца. Ex: \"Hello world , 3, 7\"  -> \"Helol rowld \"";
    public final String SHORT_DESCRIPTION = "Реверсирует подстроку в строке по индексам начала и конца.";

    public final String EXPECTED_INPUT = "Строка и индексы начала и конца подстроки, которую будут реверсировать, разделенные символом зяпятой";

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
    public void run(BufferedReader br) {
        String[] strings;
        String reversed = "";

        try {
            String string = br.readLine();
            strings = string.split(",");
            reversed = ReverseString.reverse(strings[0], Integer.parseInt(strings[1]), Integer.parseInt(strings[2]),true);
        } catch (Exception e) {
            System.err.println(ERROR_MESSAGE);
            strings = new String[]{"", "", ""};
            e.printStackTrace();
        }

        System.out.println(new StringBuilder("Результат задания: ").append(strings[0]).append(" with ").append(strings[1]).append(",").append(strings[2]).append(" -> ").append(reversed).append("\n"));
    }
}

