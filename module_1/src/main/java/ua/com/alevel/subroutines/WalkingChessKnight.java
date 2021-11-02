package ua.com.alevel.subroutines;

import ua.com.alevel.console.ConsoleSubroutine;
import ua.com.alevel.level1.ChessHelper;
import ua.com.alevel.level1.entity.ChessFigureE;
import ua.com.alevel.level1.entity.ChessPosition;

import java.io.BufferedReader;

public class WalkingChessKnight extends ConsoleSubroutine {
    public final String LONG_DESCRIPTION = "Позволяет вам понять может ли Конь походить с одной позиции на другую. Введите первое положение и выберете второе из графического представления.";
    public final String SHORT_DESCRIPTION = "Указывает может ли Конь походить с одной позиции на другую.";
    public final String SECOND_MOVE = "Выберите вашу новую позицию";
    public final String NEW_ITER = "Желаете сдвинуть коня?(Да\\Нет)";

    public final String EXPECTED_INPUT = "Две строки с указанием позиции Коня на шахматной доске. А4 \\n B6. 4А будет воспринято как неверный ввод. Принимаеться только латиница.";

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
        ChessPosition prev;
        ChessPosition next;
        boolean canWalk;
        try {
            String first_pos = bufferedReader.readLine();
            first_pos = first_pos.strip();
            if (first_pos.length() < 2) {
                throw new Exception();
            }
            char y = first_pos.charAt(0);
            int x = Integer.parseInt(first_pos.substring(1));
            prev = new ChessPosition(x, y);
            while (true) {
                System.out.println(ChessHelper.toString(prev, ChessFigureE.Knight));
                String second_pos = bufferedReader.readLine();
                second_pos = second_pos.strip();
                if (second_pos.length() < 2) {
                    throw new Exception();
                }
                y = second_pos.charAt(0);
                x = Integer.parseInt(second_pos.substring(1));
                next = new ChessPosition(x, y);
                canWalk = ChessHelper.isCanGoHere(prev, next, ChessFigureE.Knight);
                System.out.println("Результат хода: " + (canWalk ? "ход удался" : "ход нельзя совершить"));
                if (canWalk) {
                    prev = next;
                }
                System.out.println(NEW_ITER);
                String newIteration = bufferedReader.readLine();
                if (!newIteration.toLowerCase().contains("да")) {
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println(ERROR_MESSAGE);
            System.out.println(EXPECTED_INPUT);
        }
    }

}
