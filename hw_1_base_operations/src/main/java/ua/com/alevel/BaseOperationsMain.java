package ua.com.alevel;

import ua.com.alevel.console.*;
import ua.com.alevel.subroutines.*;

import java.util.*;

public class BaseOperationsMain {

    public static void main(String[] args) {
        List<ConsoleSubroutine> subroutines = new ArrayList<>();
        subroutines.add(new SumStringDigits());
        subroutines.add(new LettersCounterAndSorter());
        subroutines.add(new LessonEnd());
        ConsoleHelper.runConsoleApplication(subroutines);
    }
}
