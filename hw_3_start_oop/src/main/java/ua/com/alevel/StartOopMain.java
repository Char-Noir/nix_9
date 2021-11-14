package ua.com.alevel;

import ua.com.alevel.console.ConsoleHelper;
import ua.com.alevel.console.ConsoleSubroutine;
import ua.com.alevel.console.responce.ConsoleResponceSubroutine;
import ua.com.alevel.subroutines.*;

import java.util.ArrayList;
import java.util.List;

public class StartOopMain {
    public static void main(String[] args) {
        List<ConsoleResponceSubroutine> subroutines = new ArrayList<>();
        subroutines.add(new CreateUser());
        subroutines.add(new UpdateUser());
        subroutines.add(new DeleteUser());
        subroutines.add(new ShowUserById());
        subroutines.add(new UsersShow());
        ConsoleHelper.runConsoleResponseApplication(subroutines);
    }
}
