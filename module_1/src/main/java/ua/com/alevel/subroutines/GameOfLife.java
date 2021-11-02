package ua.com.alevel.subroutines;

import ua.com.alevel.console.ConsoleSubroutine;
import ua.com.alevel.level3.GameOfLifeHelper;

import java.io.BufferedReader;
import java.io.IOException;

public class GameOfLife extends ConsoleSubroutine {
    public final String LONG_DESCRIPTION = "Просчитывает новую итерацию игры в жизнь.";
    public final String SHORT_DESCRIPTION = "Игра в жизнь";

    public final String EXPECTED_INPUT = "Для новой итерации напечатайте что угодно длиной до 2 символов. Для выхода введите что-то длинее 2 символов.";

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
        int[][] cells = GameOfLifeHelper.generate();
        while (true) {
            String iterator;
            try {
                System.out.println(GameOfLifeHelper.toString(cells));
                iterator = bufferedReader.readLine();
                if (iterator.strip().length() > 2) {
                    break;
                }
                cells = GameOfLifeHelper.calculateNewIteration(cells);
            } catch (IOException e) {
                System.err.println(ERROR_MESSAGE);
            }
        }
    }

}
