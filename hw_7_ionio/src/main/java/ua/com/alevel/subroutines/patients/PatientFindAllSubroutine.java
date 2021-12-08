package ua.com.alevel.subroutines.patients;

import ua.com.alevel.console.annotation.ListSubroutine;
import ua.com.alevel.console.responce.ConsoleResponceSubroutine;
import ua.com.alevel.console.responce.Responce;
import ua.com.alevel.console.responce.ResponceStatus;
import ua.com.alevel.controller.impl.PatientControllerImpl;

import java.io.BufferedReader;
import java.io.IOException;

@ListSubroutine
public class PatientFindAllSubroutine extends ConsoleResponceSubroutine {

    public final String LONG_DESCRIPTION = "Показывает всех пациентов";
    public final String SHORT_DESCRIPTION = "Показать пациентов";
    public final String URL = "showall/patients";
    public final String EXPECTED_INPUT = "";
    private final PatientControllerImpl controller = new PatientControllerImpl();

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
        System.out.println("Хотите создать, отредактировать или удалить пациента?(c - create, u - update, d - delete, n - net)");
        try {
            String answer = bufferedReader.readLine();
            if (answer.startsWith("u")) {
                responce = Responce.getRedirect(responce.getMessage(), "update/patients");
            } else if (answer.startsWith("d")) {
                responce = Responce.getRedirect(responce.getMessage(), "delete/patients");
            } else if (answer.startsWith("c")) {
                responce = Responce.getRedirect(responce.getMessage(), "create/patients");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responce;
    }
}
