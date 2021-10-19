package ua.com.alevel;

import ua.com.alevel.console.*;
import ua.com.alevel.subroutines.*;

import java.util.ArrayList;
import java.util.List;

public class StringsMain {
    public static void main(String[] args) {
        List<ConsoleSubroutine> subroutines = new ArrayList<>();
        subroutines.add(new SimpleReverse());
        subroutines.add(new ReverseByWords());
        subroutines.add(new ReverseSubString());
        subroutines.add(new ReverseByIndexes());
        ConsoleHelper.runConsoleApplication(subroutines);
    }
}
