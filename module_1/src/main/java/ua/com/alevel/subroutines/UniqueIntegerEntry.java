package ua.com.alevel.subroutines;

import ua.com.alevel.console.ConsoleSubroutine;
import ua.com.alevel.level1.ArrayHelper;

import java.io.BufferedReader;
import java.util.LinkedList;
import java.util.List;

public class UniqueIntegerEntry extends ConsoleSubroutine {

    public final String LONG_DESCRIPTION = "Считает количество уникальных символов в масиве целых чисел. 1 4 5 1 1 3 -> 4";
    public final String SHORT_DESCRIPTION = "Выводит число уникальных символов";
    public final String EXPECTED_INPUT = "Строка, внутри которой каждое число разделено пробелом. Ввод значений таких как \"15\" будет расценен как число 15.";

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
        List<Integer> ints = new LinkedList<>();
        int uniques = 0;
        try {
            String line = bufferedReader.readLine();
            String[] inputs = line.split(" ");
            for (String entry :
                    inputs) {
                ints.add(Integer.parseInt(entry));
            }
            uniques = ArrayHelper.calcUniqueEntry(ints);
        } catch (Exception e) {
            System.err.println(ERROR_MESSAGE);
            System.out.println(EXPECTED_INPUT);
        }
        System.out.println(new StringBuilder("Результат задания: ").append("Для такого масива чисел").append('\n').append(ints).append('\n').append(uniques == 0 ? "нет уникальных чисел" : "вышло такое количество уникальных чисел: " + uniques));
    }
}
