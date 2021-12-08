package ua.com.alevel.subroutines.doctors;

import ua.com.alevel.console.responce.ConsoleResponceSubroutine;
import ua.com.alevel.console.responce.Responce;
import ua.com.alevel.console.responce.ResponceStatus;
import ua.com.alevel.controller.DoctorController;
import ua.com.alevel.controller.impl.DoctorControllerImpl;

import java.io.BufferedReader;
import java.time.LocalDate;

public class DoctorUpdateSubroutine extends ConsoleResponceSubroutine {

    public final String LONG_DESCRIPTION = "Обновляет доктора по Id если есть";
    public final String SHORT_DESCRIPTION = "Обновить доктора";
    public final String URL = "update/doctors";
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
        try {
            System.out.println("Пожалуйста, введите айди доктора:\n");
            long id = (Long.parseLong(bufferedReader.readLine()));
            System.out.println("Пожалуйста, введите новое имя доктора:\n");
            String name = bufferedReader.readLine();
            System.out.println("Пожалуйста, введите оценку атестации доктора:\n");
            Double assessmentOfCertification = (Double.parseDouble(bufferedReader.readLine().replace(',','.')));
            System.out.println("Пожалуйста, введите характеристику доктора:\n");
            String doctorNote = bufferedReader.readLine();
            System.out.println("Пожалуйста, введите дату сертификации доктора в формате yyyy-mm-dd:\n");
            LocalDate dateOfCertification = LocalDate.parse(bufferedReader.readLine());
            System.out.println("Пожалуйста, введите логин доктора:\n");
            String login = bufferedReader.readLine();
            System.out.println("Пожалуйста, введите пароль доктора:\n");
            String hashPassword = bufferedReader.readLine();
            Responce category = controller.findAllCategories();
            System.out.println("Пожалуйста, введите категорию доктора:\n");
            System.out.println(category.getMessage());
            String categoryS = bufferedReader.readLine();
            Responce specialization = controller.findAllSpecializations();
            System.out.println("Пожалуйста, введите специализацию доктора:\n");
            System.out.println(specialization.getMessage());
            String specializationS = bufferedReader.readLine();
            Responce responce = controller.update(id, name, assessmentOfCertification, doctorNote, dateOfCertification, login, hashPassword, categoryS, specializationS);
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
