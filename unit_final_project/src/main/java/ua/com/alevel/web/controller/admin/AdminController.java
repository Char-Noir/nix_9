package ua.com.alevel.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.alevel.facade.DoctorFacade;
import ua.com.alevel.facade.PatientFacade;
import ua.com.alevel.facade.ReceptionFacade;
import ua.com.alevel.facade.UserFacade;
import ua.com.alevel.web.controller.AbstractController;

@Controller
@RequestMapping("/admin")
public class AdminController extends AbstractController {

    private final UserFacade userFacade;
    private final DoctorFacade doctorFacade;
    private final PatientFacade patientFacade;
    private final ReceptionFacade receptionFacade;

    public AdminController(UserFacade userFacade, DoctorFacade doctorFacade, PatientFacade patientFacade, ReceptionFacade receptionFacade) {
        this.userFacade = userFacade;
        this.doctorFacade = doctorFacade;
        this.patientFacade = patientFacade;
        this.receptionFacade = receptionFacade;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("users", userFacade.count().toString());
        model.addAttribute("doctors", doctorFacade.count().toString());
        model.addAttribute("patients", patientFacade.count().toString());
        model.addAttribute("receptions", receptionFacade.count().toString());
        return "pages/admin/dashboard";
    }


}
