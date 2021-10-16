package ua.com.alevel;

import ua.com.alevel.console.ConsoleHelper;
import ua.com.alevel.console.ConsoleSubroutine;
import ua.com.alevel.subroutines.ReverseByWords;
import ua.com.alevel.subroutines.SimpleReverse;

import java.util.ArrayList;
import java.util.List;

public class StringsMain {
    public static void main(String[] args) {
        List<ConsoleSubroutine> subroutines = new ArrayList<>();
        subroutines.add(new SimpleReverse());
        subroutines.add(new ReverseByWords());
        ConsoleHelper.runConsoleApplication(subroutines);
    }
}
