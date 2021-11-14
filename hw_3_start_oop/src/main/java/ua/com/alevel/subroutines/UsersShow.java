package ua.com.alevel.subroutines;

import ua.com.alevel.console.annotation.ListSubroutine;
import ua.com.alevel.console.responce.ConsoleResponceSubroutine;
import ua.com.alevel.console.responce.Responce;
import ua.com.alevel.console.responce.ResponceStatus;
import ua.com.alevel.controller.UserController;

import java.io.BufferedReader;

@ListSubroutine
public class UsersShow extends ConsoleResponceSubroutine {

    public final String LONG_DESCRIPTION = "Показывает всех пользователей";
    public final String SHORT_DESCRIPTION = "Показать пользователей";
    public final String URL = "showall";
    public final String EXPECTED_INPUT = "";
    private final UserController controller = UserController.getInstance();

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
    public String getSubroutineURL() {
        return URL;
    }

    @Override
    public Responce run(BufferedReader bufferedReader) {
        Responce responce = controller.findAll();
        if (responce.getStatus() == ResponceStatus.OK || responce.getStatus() == ResponceStatus.OK_REDIRECT) {
            System.out.println(responce.getMessage());
        }
        return responce;
    }
}
