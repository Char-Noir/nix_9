package ua.com.alevel;

import ua.com.alevel.Util.ComparatorByValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class BaseOperationsMain {
    public static final String DELIMETR = "##########################################################";

    public static final int LESSON_START = 540;
    public static final int LESSON_DURATION = 45;
    public static final int MIDLESSON_SHORT_BREAK = 5;
    public static final int MIDLESSON_LONG_BREAK = 15;

    public static void main(String[] args) {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //Scanner input = new Scanner(System.in);

        System.out.println("Executing first Task!");
        System.out.println("Реализуйте задачу, которая принимает строку с\nконсоли и вычленяет все числа и находит их сумму");
        String forSum;
        System.out.println("Please enter your string!");
        int task1 = 0;

        try {
            //forSum = input.nextLine();
            forSum=br.readLine();
            task1 = stringToSum(forSum);
        } catch (Exception e) {
            e.getStackTrace();
        }

        System.out.printf("Result of Task: %s\n", (task1 == 0) ? "no digits in string!" : String.valueOf(task1));
        System.out.println(DELIMETR);

        System.out.println("Executing second Task!");
        System.out.println("реализуйте задачу, которая принимает строку с консоли и вычленяет все символы\n" +
                "латиницы/кириллицы и сортирует их, указывая количество вхождений каждого символа");
        String forSort;
        Map<Character,Integer> task2 = null;

        try {
            //forSort = input.nextLine();
            forSort = br.readLine();
            task2 = stringToSortedMap(forSort);
        } catch (Exception e) {
            e.getStackTrace();
        }

        System.out.println(new StringBuilder("Result of Task: ").append((task2 == null||task2.isEmpty()) ? "no characters to show!" : task2).append("\n"));
        System.out.println(DELIMETR);

        System.out.println("Executing third Task!");
        System.out.println("Определите, когда заканчивается указанный урок. Начало занятий - 9:00. \nДлина урока - 45 минут. Четный перерыв - 5 минут, нечетный перерыв - 15 минут.");

        //int lesson = input.nextInt();
        int lesson = 0;
        try {
            lesson = Integer.parseInt(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        int[] time = calculateLessonEnd(lesson);

        System.out.printf("Result of Task: Lesson № %d ends at %d:%d\n", lesson, time[0], time[1]);
    }

    //Input number of a lesson
    //Output two digits: hour and minutes of ending lesson
    private static int[] calculateLessonEnd(int lesson) {
        int minutes = (int) (LESSON_START + LESSON_DURATION * lesson + MIDLESSON_SHORT_BREAK * Math.ceil(lesson / 2.0) + MIDLESSON_LONG_BREAK * (lesson / 2) - (((lesson & 1) == 0)? MIDLESSON_LONG_BREAK:MIDLESSON_SHORT_BREAK));
        int hours = minutes / 60;
        return new int[]{hours, minutes - (hours * 60)};
    }

    //Input string of letters
    //Output sorted map of letters as keys and their entry as value
    //Sorted by value and all characters is lowercased
    private static Map<Character,Integer> stringToSortedMap(String forSort) {
        Map<Character, Integer> unsortedMap = new HashMap<>();
        ComparatorByValue comparator = new ComparatorByValue(unsortedMap);

        for (int i = 0; i < forSort.length(); i++) {
            Character character = Character.toLowerCase(forSort.charAt(i));
            if (Character.isLetter(character)) {
                Integer value = unsortedMap.get(character);
                unsortedMap.put(character,
                        (value == null) ? 1 : value + 1);
            }
        }

        Map<Character,Integer> sortedMap = new TreeMap<>(comparator);
        sortedMap.putAll(unsortedMap);

        return sortedMap;
    }

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

}
