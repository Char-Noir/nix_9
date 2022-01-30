package ua.com.alevel.web.controller.entity;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.alevel.facade.PatientFacade;
import ua.com.alevel.facade.UserFacade;
import ua.com.alevel.web.controller.AbstractController;
import ua.com.alevel.web.dto.request.patient.PatientRequestDto;
import ua.com.alevel.web.dto.response.patient.PatientResponseDto;
import ua.com.alevel.web.dto.response.user.UserResponseDto;

@Validated
@Controller
@RequestMapping("/patient")
public class PatientController extends AbstractController {

    private final PatientFacade patientFacade;
    private final UserFacade userFacade;

    public PatientController(PatientFacade patientFacade, UserFacade userFacade) {
        this.patientFacade = patientFacade;
        this.userFacade = userFacade;
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("patient", new PatientRequestDto());
        return "pages/patients/add";
    }

    @GetMapping("/edit")
    public String update(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        UserResponseDto user = userFacade.findByEmail(username);
        PatientResponseDto patient = patientFacade.findByUserId(user.getId());
        model.addAttribute("patient", new PatientRequestDto());
        model.addAttribute("patientOld", patient);
        return "pages/patients/update";
    }

    @PostMapping("/edit/post")
    public String updatePost(@ModelAttribute(name = "patient") PatientRequestDto dto) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        System.out.println(dto);
        UserResponseDto user = userFacade.findByEmail(username);
        dto.setUserId(user.getId());
        PatientResponseDto patient = patientFacade.findByUserId(user.getId());
        patientFacade.update(dto, patient.getId());
        return "redirect:/account/patient/";
    }

    @PostMapping("/add/post")
    public String addPost(@ModelAttribute PatientRequestDto dto) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        System.out.println(dto);
        UserResponseDto user = userFacade.findByEmail(username);
        dto.setUserId(user.getId());
        patientFacade.create(dto);
        return "redirect:/logout";
    }
}
