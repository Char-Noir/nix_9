package ua.com.alevel.web.controller.personal;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.alevel.facade.DoctorFacade;
import ua.com.alevel.facade.PatientFacade;
import ua.com.alevel.facade.UserFacade;
import ua.com.alevel.web.dto.response.doctor.DoctorResponseDto;
import ua.com.alevel.web.dto.response.patient.PatientResponseDto;
import ua.com.alevel.web.dto.response.user.UserResponseDto;

@Validated
@Controller
@RequestMapping("/account")
public class AdditionalEntityController {

    private final PatientFacade patientFacade;
    private final DoctorFacade doctorFacade;
    private final UserFacade userFacade;

    public AdditionalEntityController(PatientFacade patientFacade, DoctorFacade doctorFacade, UserFacade userFacade) {
        this.patientFacade = patientFacade;
        this.doctorFacade = doctorFacade;
        this.userFacade = userFacade;
    }

    @GetMapping("/patient")
    public String patient(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        UserResponseDto user = userFacade.findByEmail(username);
        PatientResponseDto patient = patientFacade.findByUserId(user.getId());
        model.addAttribute("patient", patient);
        return "pages/personal/patient";
    }

    @GetMapping("/doctor")
    public String doctor(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        UserResponseDto user = userFacade.findByEmail(username);
        System.out.println(user);
        DoctorResponseDto doctor = doctorFacade.findByUserId(user.getId());
        model.addAttribute("doctor", doctor);
        return "pages/personal/doctor";
    }
}
