package ua.com.alevel;

import ua.com.alevel.console.ConsoleHelper;
import ua.com.alevel.console.ConsoleSubroutine;
import ua.com.alevel.subroutines.DateOutputer;
import ua.com.alevel.subroutines.FindEasiestRoute;
import ua.com.alevel.subroutines.UniqueNameFinder;

import java.util.ArrayList;
import java.util.List;

public class ModuleSecondMain {

    public static void main(String[] args) {
        List<ConsoleSubroutine> list = new ArrayList<>();
        list.add(new DateOutputer());
        list.add(new UniqueNameFinder());
        list.add(new FindEasiestRoute());
        ConsoleHelper.runConsoleApplication(list);
    }

}
