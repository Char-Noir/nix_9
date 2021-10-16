package ua.com.alevel.subroutines;

import ua.com.alevel.ReverseString;
import ua.com.alevel.console.ConsoleSubroutine;

import java.io.BufferedReader;

public class SimpleReverse extends ConsoleSubroutine {

    public final String LONG_DESCRIPTION = "Возвращает строку, которая является обратной входной. Ex: \"Hello world\" -> \"dlrow olleH \"";
    public final String SHORT_DESCRIPTION = "Просто реверсирует строку.";

    @Override
    public String getShortDescription() {
        return SHORT_DESCRIPTION;
    }

    @Override
    public String getLongDescription() {
        return LONG_DESCRIPTION;
    }

    @Override
    public void run(BufferedReader br) {
        String normal = "";
        String reversed = "";

        try {
            normal = br.readLine();
            reversed = ReverseString.reverse(normal);
        } catch (Exception e) {
            System.err.println(ERROR_MESSAGE);
            e.getStackTrace();
        }

        System.out.println(new StringBuilder("Результат задания: ").append(normal).append(" -> ").append(reversed).append("\n"));
    }
}
