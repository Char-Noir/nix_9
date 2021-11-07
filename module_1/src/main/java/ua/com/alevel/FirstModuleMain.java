package ua.com.alevel;

import ua.com.alevel.console.ConsoleHelper;
import ua.com.alevel.console.ConsoleSubroutine;
import ua.com.alevel.subroutines.*;

import java.util.ArrayList;
import java.util.List;

public class FirstModuleMain {

    public static void main(String[] args) {
        List<ConsoleSubroutine> subroutines = new ArrayList<>();
        subroutines.add(new UniqueIntegerEntry());
        subroutines.add(new WalkingChessKnight());
        subroutines.add(new TriangleArea());
        subroutines.add(new BracketsInString());
        subroutines.add(new BinaryTreeDepth());
        subroutines.add(new GameOfLife());
        subroutines.add(new GameOfLifeAuto());
        ConsoleHelper.runConsoleApplication(subroutines);
    }
}