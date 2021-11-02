package ua.com.alevel.level1;

import ua.com.alevel.level1.entity.ChessFigureE;
import ua.com.alevel.level1.entity.ChessPosition;

public class ChessHelper {
    private static final String WHITE_PIECE = "  ";
    private static final String BLACK_PIECE = "██";

    public static boolean isCanGoHere(ChessPosition start, ChessPosition end, ChessFigureE figure) {
        if (start == null || end == null || figure == null) {
            throw new NullPointerException();
        }
        switch (figure) {
            case Knight -> {
                int x = differenceX(start, end);
                int y = differenceY(start, end);
                System.out.println(x);
                System.out.println(y);
                return (x == 2 && y == 1) || (y == 2 && x == 1);
            }
        }
        return false;
    }

    public static int differenceX(ChessPosition start, ChessPosition end) {
        return Math.abs(start.getNumberPosition() - end.getNumberPosition());
    }

    public static int differenceY(ChessPosition start, ChessPosition end) {
        return Math.min((Math.abs(start.getCharacterPosition() - end.getCharacterPosition())), (Math.abs(Math.max(start.getCharacterPosition(), end.getCharacterPosition()) - (Math.min(start.getCharacterPosition(), end.getCharacterPosition()) + 26))));
    }

    public static String toString(int x, char y, ChessFigureE figure) {
        StringBuilder stringBuilder = new StringBuilder();
        int[] rows = new int[7];
        for (int i = -3; i < 4; i++) {
            rows[i + 3] = i + x;
        }
        char[] cols = new char[7];
        stringBuilder.append("\t ");
        for (int i = -3; i < 4; i++) {
            cols[i + 3] = (char) (((i + ((int) y - 65 + 130)) % 26) + 65);
            stringBuilder.append(cols[i + 3]).append(' ');
        }
        stringBuilder.append('\n');
        for (int i = 0; i < 7; i++) {//throw rows
            stringBuilder.append('\t').append(rows[i]);
            if (i == 3) {//row with figure
                stringBuilder.append(BLACK_PIECE).append(WHITE_PIECE).append(BLACK_PIECE).append(figure.toString()).append(BLACK_PIECE).append(WHITE_PIECE).append(BLACK_PIECE).append('\n');
            } else if (i % 2 == 0) {//odd row
                stringBuilder.append(WHITE_PIECE).append(BLACK_PIECE).append(WHITE_PIECE).append(BLACK_PIECE).append(WHITE_PIECE).append(BLACK_PIECE).append(WHITE_PIECE).append('\n');
            } else {
                stringBuilder.append(BLACK_PIECE).append(WHITE_PIECE).append(BLACK_PIECE).append(WHITE_PIECE).append(BLACK_PIECE).append(WHITE_PIECE).append(BLACK_PIECE).append('\n');
            }
        }
        return stringBuilder.toString();
    }

    public static String toString(ChessPosition position, ChessFigureE figure) {
        return toString(position.getNumberPosition(), position.getCharacterPosition(), figure);
    }
}
