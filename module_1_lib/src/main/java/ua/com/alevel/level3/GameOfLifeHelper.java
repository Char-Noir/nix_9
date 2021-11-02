package ua.com.alevel.level3;

import java.util.Random;

public class GameOfLifeHelper {
    private static final String DEAD_PIECE = "  ";
    private static final String ALIVE_PIECE = "██";
    private static final String HORIZONTAL_PIECE = "──";
    private static final String VERTICAL_PIECE = "│";

    public static int[][] calculateNewIteration(int[][] oldCells) {
        int M = oldCells.length;
        int N = oldCells[0].length;
        int[][] newCells = new int[M][N];
        for (int l = 1; l < M - 1; l++) {
            for (int m = 1; m < N - 1; m++) {
                int aliveNeighbours = countNeighbours(oldCells, l, m);
                if ((oldCells[l][m] == 1) && (aliveNeighbours < 2)) {
                    newCells[l][m] = 0;
                } else if ((oldCells[l][m] == 1) && (aliveNeighbours > 3)) {
                    newCells[l][m] = 0;
                } else if ((oldCells[l][m] == 0) && (aliveNeighbours == 3)) {
                    newCells[l][m] = 1;
                } else {
                    newCells[l][m] = oldCells[l][m];
                }
            }
        }
        return newCells;
    }

    public static int countNeighbours(int[][] cells, int x, int y) {
        int sum = 0;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                sum += cells[i + x][j + y];
            }
        }
        sum -= cells[x][y];
        return sum;
    }

    public static String toString(int[][] cells) {
        StringBuilder sb = new StringBuilder();
        int M = cells.length;
        int N = cells[0].length;
        sb.append(' ');
        sb.append(HORIZONTAL_PIECE.repeat(N)).append('\n');
        for (int i = 1; i < M; i++) {
            sb.append(VERTICAL_PIECE);
            for (int j = 0; j < N; j++) {
                sb.append((cells[i][j] == 0) ? DEAD_PIECE : ALIVE_PIECE);
            }
            sb.append(VERTICAL_PIECE).append('\n');
        }
        sb.append(' ');
        sb.append(HORIZONTAL_PIECE.repeat(N)).append('\n');
        return sb.toString();
    }

    public static int[][] generate() {
        int[][] cells = new int[10][10];
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                cells[i][j] = random.nextInt(2);
            }
        }
        return cells;
    }
}
