package ua.com.alevel;

import ua.com.alevel.console.ConsoleHelper;
import ua.com.alevel.console.responce.ConsoleResponceSubroutine;
import ua.com.alevel.controller.BaseController;
import ua.com.alevel.subroutines.doctors.*;
import ua.com.alevel.subroutines.patients.*;
import ua.com.alevel.subroutines.receptions.*;

import java.util.ArrayList;
import java.util.List;

public class IoNioMain {

    public static void main(String[] args) {
        List<ConsoleResponceSubroutine> subroutines = new ArrayList<>();
        //Patient
        subroutines.add(new PatientCreateSubroutine());
        subroutines.add(new PatientUpdateSubroutine());
        subroutines.add(new PatientDeleteByIdSubroutine());
        subroutines.add(new PatientFindByIdSubroutine());
        subroutines.add(new PatientFindAllSubroutine());
        //Doctors
        subroutines.add(new DoctorCreateSubroutine());
        subroutines.add(new DoctorUpdateSubroutine());
        subroutines.add(new DoctorDeleteByIdSubroutine());
        subroutines.add(new DoctorFindByIdSubroutine());
        subroutines.add(new DoctorFindAllSubroutine());
        //Receptions
        subroutines.add(new ReceptionCreateSubroutine());
        subroutines.add(new ReceptionUpdateSubroutine());
        subroutines.add(new ReceptionDeleteByIdSubroutine());
        subroutines.add(new ReceptionFindByIdSubroutine());
        subroutines.add(new ReceptionFindAllSubroutine());
        subroutines.add(new ReceptionFindByPatientIdSubroutine());
        subroutines.add(new ReceptionFindByDoctorIdSubroutine());
        ConsoleHelper.runConsoleResponseApplication(subroutines, BaseController.class);
    }
}
