package ua.com.alevel.subroutines.patients;

import ua.com.alevel.console.responce.ConsoleResponceSubroutine;
import ua.com.alevel.console.responce.Responce;
import ua.com.alevel.console.responce.ResponceStatus;
import ua.com.alevel.controller.PatientContoller;
import ua.com.alevel.controller.impl.PatientControllerImpl;

import java.io.BufferedReader;
import java.time.LocalDate;

public class PatientCreateSubroutine extends ConsoleResponceSubroutine {

    public final String LONG_DESCRIPTION = "Создает пациента";
    public final String SHORT_DESCRIPTION = "Создать пациента";
    public final String URL = "create/patients";
    public final String EXPECTED_INPUT = "";
    private final PatientContoller controller = new PatientControllerImpl();

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
            System.out.println("Пожалуйста, введите новое имя пациента:\n");
            String name = bufferedReader.readLine();
            System.out.println("Пожалуйста, введите дату рождения пациента в формате yyyy-mm-dd:\n");
            LocalDate dateOfBirth = LocalDate.parse(bufferedReader.readLine());
            System.out.println("Пожалуйста, введите номер телефона пациента:\n");
            String phoneNumber = bufferedReader.readLine();
            System.out.println("Пожалуйста, введите документы пациента:\n");
            String userDocuments = bufferedReader.readLine();
            System.out.println("Пожалуйста, введите логин пациента:\n");
            String login = bufferedReader.readLine();
            System.out.println("Пожалуйста, введите пароль пациента:\n");
            String hashPassword = bufferedReader.readLine();
            Responce responce = controller.create(name, dateOfBirth, phoneNumber, userDocuments, login, hashPassword);
            if (responce.getStatus() == ResponceStatus.OK || responce.getStatus() == ResponceStatus.OK_REDIRECT) {
                System.out.println(responce.getMessage());
            } else {
                System.out.println(responce.getErrorMessage());
            }
            return responce;
        } catch (Exception e) {
            System.err.println(ERROR_MESSAGE);
            System.out.println(EXPECTED_INPUT);
            return Responce.getError("Parse problem", e.getMessage());
        }
    }
}
