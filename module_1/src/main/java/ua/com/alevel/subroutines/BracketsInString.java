package ua.com.alevel.subroutines;

import ua.com.alevel.console.ConsoleSubroutine;
import ua.com.alevel.level2.BracketsHelper;

import java.io.BufferedReader;

public class BracketsInString extends ConsoleSubroutine {

    public final String LONG_DESCRIPTION = "Проверяет валидность строки. Строка валидна, если все открытые скобочки закрыты соответствующими и в нужном порядке.";
    public final String SHORT_DESCRIPTION = "Скобочки в строке";

    @Override
    public String getShortDescription() {
        return SHORT_DESCRIPTION;
    }

    @Override
    public String getLongDescription() {
        return LONG_DESCRIPTION;
    }

    @Override
    public void run(BufferedReader bufferedReader) {
        String brackets = null;
        boolean isValid = false;
        try {
            brackets = bufferedReader.readLine();
            isValid = BracketsHelper.isStringHaveValidBrackets(brackets);
        } catch (Exception e) {
            System.err.println(ERROR_MESSAGE);
            System.out.println(EXPECTED_INPUT);
        }
        System.out.println(new StringBuilder("Результат задания: ").append(" строка \"").append(brackets == null ? "нет строки" : brackets).append("\" ").append(isValid ? "валидна" : "не валидна").append("\n"));
    }
}
