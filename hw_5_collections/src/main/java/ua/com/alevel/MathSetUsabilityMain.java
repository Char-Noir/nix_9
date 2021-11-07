package ua.com.alevel;

import ua.com.alevel.console.ConsoleHelper;
import ua.com.alevel.console.ConsoleSubroutine;
import ua.com.alevel.subroutines.constructors.*;
import ua.com.alevel.subroutines.methods.*;

import java.util.ArrayList;
import java.util.List;

public class MathSetUsabilityMain {
    public static MathSet mathSet;

    public static void main(String[] args) {
        mathSet = new MathSet();
        List<ConsoleSubroutine> subroutines = new ArrayList<>();
        subroutines.add(new MathSetDefaultConstructorUsing());
        subroutines.add(new MathSetCapacityConstructorUsing());
        subroutines.add(new MathSetArrayOfNumbersConstructorUsing());
        subroutines.add(new MathSetConstructorFromNativeArray());
        subroutines.add(new MathSetConstructorFromAnotherMathSet());
        subroutines.add(new MathSetConstructorFromMathSetArray());
        subroutines.add(new MathSetAddNumber());
        subroutines.add(new MathSetAddArray());
        subroutines.add(new MathSetJoinMathSet());
        subroutines.add(new MathSetJoinMathSets());
        subroutines.add(new MathSetInterMathSet());
        subroutines.add(new MathSetInterMathSets());
        subroutines.add(new MathSetSortDesc());
        subroutines.add(new MathSetSortDescIndex());
        subroutines.add(new MathSetSortDescNumber());
        subroutines.add(new MathSetSortAsc());
        subroutines.add(new MathSetSortAscIndex());
        subroutines.add(new MathSetSortAscNumber());
        subroutines.add(new MathSetGetByIndex());
        subroutines.add(new MathSetGetMax());
        subroutines.add(new MathSetGetMin());
        subroutines.add(new MathSetGetMedian());
        subroutines.add(new MathSetGetAverage());
        subroutines.add(new MathSetToArray());
        subroutines.add(new MathSetToArrayIndex());
        subroutines.add(new MathSetCut());
        subroutines.add(new MathSetClear());
        subroutines.add(new MathSetClearNumbers());
        ConsoleHelper.runConsoleApplication(subroutines);
    }
}
