package ua.com.alevel.subroutines.doctors;

import ua.com.alevel.console.annotation.ListSubroutine;
import ua.com.alevel.console.responce.ConsoleResponceSubroutine;
import ua.com.alevel.console.responce.Responce;
import ua.com.alevel.console.responce.ResponceStatus;
import ua.com.alevel.controller.DoctorController;
import ua.com.alevel.controller.impl.DoctorControllerImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

@ListSubroutine
public class DoctorFindAllSubroutine extends ConsoleResponceSubroutine {

    public final String LONG_DESCRIPTION = "Показывает всех докторов";
    public final String SHORT_DESCRIPTION = "Показать докторов";
    public final String URL = "showall/doctors";
    public final String EXPECTED_INPUT = "";
    private final DoctorController controller = new DoctorControllerImpl();

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
        System.out.println("Хотите создать, отредактировать или удалить доктора?(c - create, u - update, d - delete, n - net)");
        try {
            String answer = bufferedReader.readLine();
            if (answer.startsWith("u")) {
                responce = Responce.getRedirect(responce.getMessage(), "update/doctors");
            } else if (answer.startsWith("d")) {
                responce = Responce.getRedirect(responce.getMessage(), "delete/doctors");
            } else if (answer.startsWith("c")) {
                responce = Responce.getRedirect(responce.getMessage(), "create/doctors");
            }
        } catch (IOException e) {
            return Responce.getError(e.getMessage(), Arrays.toString(e.getStackTrace()));
        }
        return responce;
    }
}
