package ua.com.alevel.subroutines.receptions;

import ua.com.alevel.console.responce.ConsoleResponceSubroutine;
import ua.com.alevel.console.responce.Responce;
import ua.com.alevel.console.responce.ResponceStatus;
import ua.com.alevel.controller.ReceptionController;
import ua.com.alevel.controller.impl.ReceptionControllerImpl;

import java.io.BufferedReader;

public class ReceptionFindByIdSubroutine extends ConsoleResponceSubroutine {

    public final String LONG_DESCRIPTION = "Показывает приём по Id если есть";
    public final String SHORT_DESCRIPTION = "Показать приём";
    public final String URL = "showbyid/receptions";
    public final String EXPECTED_INPUT = "Целое число - Id приёма";
    private final ReceptionController controller = new ReceptionControllerImpl();

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
            String str = bufferedReader.readLine();
            long id = Long.parseLong(str);
            Responce responce = controller.findById(id);
            if (responce.getStatus() == ResponceStatus.OK || responce.getStatus() == ResponceStatus.OK_REDIRECT) {
                System.out.println(responce.getMessage());
                System.out.println("Хотите создать, отредактировать или удалить приём?(c - create, u - update, d - delete, n - net)");
                String answer = bufferedReader.readLine();
                if (answer.startsWith("u")) {
                    responce = Responce.getRedirect(responce.getMessage(), "update/receptions");
                } else if (answer.startsWith("d")) {
                    responce = Responce.getRedirect(responce.getMessage(), "delete/receptions");
                } else if (answer.startsWith("c")) {
                    responce = Responce.getRedirect(responce.getMessage(), "create/receptions");
                }
            } else {
                System.err.println(responce.getErrorMessage());
            }

            return responce;
        } catch (Exception e) {
            System.err.println(ERROR_MESSAGE);
            System.out.println(EXPECTED_INPUT);
            return Responce.getError("Long parse problem", e.getMessage());
        }
    }
}
