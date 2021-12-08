package ua.com.alevel.subroutines.receptions;

import ua.com.alevel.console.responce.ConsoleResponceSubroutine;
import ua.com.alevel.console.responce.Responce;
import ua.com.alevel.console.responce.ResponceStatus;
import ua.com.alevel.controller.ReceptionController;
import ua.com.alevel.controller.impl.ReceptionControllerImpl;

import java.io.BufferedReader;
import java.time.LocalDate;
import java.time.LocalTime;

public class ReceptionUpdateSubroutine extends ConsoleResponceSubroutine {

    public final String LONG_DESCRIPTION = "Обновляет приём по Id если есть";
    public final String SHORT_DESCRIPTION = "Обновить приём";
    public final String URL = "update/receptions";
    public final String EXPECTED_INPUT = "";
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
            System.out.println("Пожалуйста, введите айди приёма:\n");
            long id = (Long.parseLong(bufferedReader.readLine()));
            System.out.println("Пожалуйста, введите дату приёма в формате `yyyy-mm-dd`:\n");
            LocalDate dateOfReception = LocalDate.parse(bufferedReader.readLine());
            System.out.println("Пожалуйста, введите время приёма в формате `hh:mm` :\n");
            LocalTime receptionTime = LocalTime.parse(bufferedReader.readLine());
            System.out.println("Пожалуйста, введите комментарий приёма :\n");
            String reviewComment = bufferedReader.readLine();
            System.out.println("Пожалуйста, введите цену приёма:\n");
            Double receptionPrice = Double.parseDouble(bufferedReader.readLine().replace(',','.'));
            Responce responce = controller.update(id, dateOfReception, receptionTime, reviewComment, receptionPrice);
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
