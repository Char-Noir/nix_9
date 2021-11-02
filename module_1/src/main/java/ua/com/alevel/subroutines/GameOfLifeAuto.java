package ua.com.alevel.subroutines;

import ua.com.alevel.console.ConsoleSubroutine;
import ua.com.alevel.level3.GameOfLifeHelper;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameOfLifeAuto extends ConsoleSubroutine {
    public static boolean isWorking = true;
    public static int[][] cells;
    public static BufferedReader br;
    public final String LONG_DESCRIPTION = "Просчитывает новую итерацию игры в жизнь автоматически";
    public final String SHORT_DESCRIPTION = "Игра в жизнь. Автоматическая Возможны лаги!";
    public final String EXPECTED_INPUT = "Чтобы выйти из режима напишите нечто длинее 0 символов.";
    Callable<Void> callable1 = new Callable<>() {
        @Override
        public Void call() throws Exception {
            Thread.sleep(5000);
            while (isWorking) {
                Thread.sleep(1000);
                System.out.println(GameOfLifeHelper.toString(cells));
                cells = GameOfLifeHelper.calculateNewIteration(cells);
            }
            return null;
        }
    };

    Callable<Void> callable2 = new Callable<>() {
        @Override
        public Void call() throws Exception {
            String iterator = br.readLine();
            if (iterator.strip().length() > 0) {
                isWorking = false;
            }
            return null;
        }
    };

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
        initialize(bufferedReader);
        ExecutorService executor = Executors.newFixedThreadPool(2);
        List<Callable<Void>> taskList = new ArrayList<>();
        taskList.add(callable1);
        taskList.add(callable2);
        try {
            executor.invokeAll(taskList);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

    public void initialize(BufferedReader bufferedReader) {
        cells = GameOfLifeHelper.generate();
        br = bufferedReader;
    }

}
