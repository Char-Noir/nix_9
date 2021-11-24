package ua.com.alevel.subroutines.receptions;

import ua.com.alevel.console.responce.ConsoleResponceSubroutine;
import ua.com.alevel.console.responce.Responce;
import ua.com.alevel.console.responce.ResponceStatus;
import ua.com.alevel.controller.ReceptionController;
import ua.com.alevel.controller.impl.ReceptionControllerImpl;

import java.io.BufferedReader;

public class ReceptionDeleteByIdSubroutine extends ConsoleResponceSubroutine {

    public final String LONG_DESCRIPTION = "Удаляет приём по Id если есть";
    public final String SHORT_DESCRIPTION = "Удалить приём";
    public final String URL = "delete/receptions";
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
            Responce responce = controller.delete(id);
            if (responce.getStatus() == ResponceStatus.OK || responce.getStatus() == ResponceStatus.OK_REDIRECT) {
                System.out.println(responce.getMessage());
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
