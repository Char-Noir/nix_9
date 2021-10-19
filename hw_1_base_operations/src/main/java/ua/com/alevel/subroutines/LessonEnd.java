package ua.com.alevel.subroutines;

import ua.com.alevel.console.ConsoleSubroutine;

import java.io.BufferedReader;
import java.io.IOException;

public final class LessonEnd extends ConsoleSubroutine {

    public static final int LESSON_START = 540;
    public static final int LESSON_DURATION = 45;
    public static final int MIDLESSON_SHORT_BREAK = 5;
    public static final int MIDLESSON_LONG_BREAK = 15;

    public final String LONG_DESCRIPTION = "Определяет, когда заканчивается указанный урок от 1 до 10 включительно. Начало занятий - 9:00. \nДлина урока - 45 минут. Четный перерыв - 5 минут, нечетный перерыв - 15 минут.";
    public final String SHORT_DESCRIPTION = "Показывает время окончания любого урока от 1 до 10 включительно.";

    private final String ERROR_MESSAGE = "Неверно введенный данные. Напоминаю допустимые значения номера урока от 1 до 10 включительно!";
    public final String EXPECTED_INPUT = "Число от 1 до 10 включительно";

    //Input number of a lesson
    //Output two digits: hour and minutes of ending lesson
    private static int[] calculateLessonEnd(int lesson) {
        int minutes = (int) (LESSON_START + LESSON_DURATION * lesson + MIDLESSON_SHORT_BREAK * Math.ceil(lesson / 2.0) + MIDLESSON_LONG_BREAK * (lesson / 2) - (((lesson & 1) == 0) ? MIDLESSON_LONG_BREAK : MIDLESSON_SHORT_BREAK));
        int hours = minutes / 60;
        return new int[]{hours, minutes - (hours * 60)};
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
    public String getExpectedInput() {
        return EXPECTED_INPUT;
    }

    @Override
    public void run(BufferedReader br) {

        //int lesson = input.nextInt();
        int lesson = 0;
        try {
            lesson = Integer.parseInt(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println(ERROR_MESSAGE);
        }

        if (checkInputData(lesson)) {
            System.err.println(ERROR_MESSAGE);
        }

        int[] time = calculateLessonEnd(lesson);

        System.out.printf("Результат задания: Урок № %d заканчивается в %d:%d\n", lesson, time[0], time[1]);
    }

    private boolean checkInputData(int lesson) {
        return (lesson <= 0 || lesson > 10);
    }
}
