package ua.com.alevel.subroutines;

import ua.com.alevel.comparator.ComparatorByValue;
import ua.com.alevel.console.ConsoleSubroutine;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class LettersCounterAndSorter extends ConsoleSubroutine {

    public final String LONG_DESCRIPTION = "Задание принимает строку с консоли и вычленяет все символы\nлатиницы/кириллицы и сортирует их, указывая количество вхождений каждого символа";
    public final String SHORT_DESCRIPTION = "Подсчет входных символов и их сортировка";

    //Input string of letters
    //Output sorted map of letters as keys and their entry as value
    //Sorted by value and all characters is lowercased
    private static Map<Character, Integer> stringToSortedMap(String forSort) {
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

        Map<Character, Integer> sortedMap = new TreeMap<>(comparator);
        sortedMap.putAll(unsortedMap);

        return sortedMap;
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

        String forSort;
        Map<Character, Integer> task2 = null;

        try {
            //forSort = input.nextLine();
            forSort = br.readLine();
            task2 = stringToSortedMap(forSort);
        } catch (Exception e) {
            System.err.println(ERROR_MESSAGE);
            e.getStackTrace();
        }

        System.out.println(new StringBuilder("Результат задания: ").append((task2 == null || task2.isEmpty()) ? "нет символов для вывода!" : task2).append("\n"));

    }
}
