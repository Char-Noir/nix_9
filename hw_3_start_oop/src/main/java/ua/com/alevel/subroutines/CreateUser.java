package ua.com.alevel.subroutines;

import ua.com.alevel.console.responce.ConsoleResponceSubroutine;
import ua.com.alevel.console.responce.Responce;
import ua.com.alevel.console.responce.ResponceStatus;
import ua.com.alevel.controller.UserController;

import java.io.BufferedReader;

public class CreateUser extends ConsoleResponceSubroutine {
    public final String LONG_DESCRIPTION = "Создает пользователя";
    public final String SHORT_DESCRIPTION = "Создать пользователя";
    public final String URL = "create";
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
        try {
            System.out.println("Пожалуйста, введите имя пользователя:\n");
            String name = bufferedReader.readLine();
            System.out.println("Пожалуйста, введите возраст пользователя:\n");
            String str = bufferedReader.readLine();
            int age = (Integer.parseInt(str));
            Responce responce = controller.create(age, name);
            if (responce.getStatus() == ResponceStatus.OK || responce.getStatus() == ResponceStatus.OK_REDIRECT) {
                System.out.println(responce.getMessage());
            } else {
                System.out.println(responce.getErrorMessage());
            }
            return responce;
        } catch (Exception e) {
            System.err.println(ERROR_MESSAGE);
            System.out.println(EXPECTED_INPUT);
            return Responce.getError("Long parse problem", e.getMessage());
        }
    }
}
