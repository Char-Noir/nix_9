package ua.com.alevel.subroutines.receptions;

import ua.com.alevel.console.responce.ConsoleResponceSubroutine;
import ua.com.alevel.console.responce.Responce;
import ua.com.alevel.console.responce.ResponceStatus;
import ua.com.alevel.controller.DoctorController;
import ua.com.alevel.controller.PatientContoller;
import ua.com.alevel.controller.ReceptionController;
import ua.com.alevel.controller.impl.DoctorControllerImpl;
import ua.com.alevel.controller.impl.PatientControllerImpl;
import ua.com.alevel.controller.impl.ReceptionControllerImpl;

import java.io.BufferedReader;
import java.time.LocalDate;
import java.time.LocalTime;

public class ReceptionCreateSubroutine extends ConsoleResponceSubroutine {

    public final String LONG_DESCRIPTION = "Создает приём";
    public final String SHORT_DESCRIPTION = "Создать приём";
    public final String URL = "create/receptions";
    public final String EXPECTED_INPUT = "";
    private final ReceptionController controller = new ReceptionControllerImpl();
    private final PatientContoller pController = new PatientControllerImpl();
    private final DoctorController dController = new DoctorControllerImpl();


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
            System.out.println("Пожалуйста, введите дату приёма в формате `yyyy-mm-dd`:\n");
            LocalDate dateOfReception = LocalDate.parse(bufferedReader.readLine());
            System.out.println("Пожалуйста, введите время приёма в формате `hh:mm` :\n");
            LocalTime receptionTime = LocalTime.parse(bufferedReader.readLine());
            System.out.println("Пожалуйста, введите комментарий приёма :\n");
            String reviewComment = bufferedReader.readLine();
            System.out.println("Пожалуйста, введите цену приёма:\n");
            Double receptionPrice = Double.parseDouble(bufferedReader.readLine().replace(',', '.'));
            Responce patients = pController.findAll();
            System.out.println("Пожалуйста, введите айди пациента:\n");
            System.out.println(patients.getMessage());
            Long patientId = (Long.parseLong(bufferedReader.readLine()));
            Responce doctors = dController.findAll();
            System.out.println("Пожалуйста, введите айди врача:\n");
            System.out.println(doctors.getMessage());
            Long doctorId = (Long.parseLong(bufferedReader.readLine()));

            Responce responce = controller.create(dateOfReception, receptionTime, reviewComment, receptionPrice, doctorId, patientId);
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
