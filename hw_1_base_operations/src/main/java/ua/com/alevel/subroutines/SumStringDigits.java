package ua.com.alevel.subroutines;

import ua.com.alevel.console.ConsoleSubroutine;

import java.io.BufferedReader;

public class SumStringDigits extends ConsoleSubroutine {

    public final String SHORT_DESCRIPTION = "Сумма всех цифр строки";
    public final String LONG_DESCRIPTION = "Задача принимает строку с\nконсоли и вычленяет все цифры и находит их сумму";

    //Input string with integers within
    //Output sum of one-digit integers in string
    private static int stringToSum(String input) {
        int sum = 0;
        for (int i = 0; i < input.length(); i++) {
            char character = input.charAt(i);
            if (Character.isDigit(character)) {
                sum += Character.getNumericValue(character);
            }
        }
        return sum;
    }

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
        String forSum;
        int sum = 0;

        try {
            //forSum = input.nextLine();
            forSum = br.readLine();
            sum = stringToSum(forSum);
        } catch (Exception e) {
            System.err.println(ERROR_MESSAGE);
            e.getStackTrace();
        }

        System.out.printf("Результат задания: %s\n", (sum == 0) ? "в строке нет цифр!" : String.valueOf(sum));
    }
}
