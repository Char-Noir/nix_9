package ua.com.alevel.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.alevel.facade.DoctorFacade;
import ua.com.alevel.facade.PatientFacade;
import ua.com.alevel.facade.UserFacade;
import ua.com.alevel.web.controller.AbstractController;

@Controller
@RequestMapping("/admin")
public class AdminController extends AbstractController {

    private final UserFacade userFacade;
    private final DoctorFacade doctorFacade;
    private final PatientFacade patientFacade;

    public AdminController(UserFacade userFacade, DoctorFacade doctorFacade, PatientFacade patientFacade) {
        this.userFacade = userFacade;
        this.doctorFacade = doctorFacade;
        this.patientFacade = patientFacade;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("users", userFacade.count());
        model.addAttribute("doctors", doctorFacade.count());
        model.addAttribute("patients", patientFacade.count());
        model.addAttribute("receptions", 0);
        return "pages/admin/dashboard";
    }


}
