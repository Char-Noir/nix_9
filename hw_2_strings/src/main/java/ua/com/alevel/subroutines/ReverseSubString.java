package ua.com.alevel.subroutines;

import ua.com.alevel.ReverseString;
import ua.com.alevel.console.ConsoleSubroutine;

import java.io.BufferedReader;

public class ReverseSubString extends ConsoleSubroutine {

    public final String LONG_DESCRIPTION = "Возвращает строку, в которой реверсирована подстрока. Ex: \"Hello world\" , \"world\" -> \"Hello dlrow \"";
    public final String SHORT_DESCRIPTION = "Реверсирует подстроку в строке.";

    public final String EXPECTED_INPUT = "Строка и подстрока, которую будут реверсировать, разделенные символом зяпятой";

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
            reversed = ReverseString.reverse(strings[0], strings[1].strip());
        } catch (Exception e) {
            System.err.println(ERROR_MESSAGE);
            strings= new String[]{"", ""};
            e.printStackTrace();
        }

        System.out.println(new StringBuilder("Результат задания: ").append(strings[0]).append(" with ").append(strings[1]).append(" -> ").append(reversed).append("\n"));
    }
}

