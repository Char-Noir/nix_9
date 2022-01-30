package ua.com.alevel.web.controller.personal;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.alevel.facade.UserFacade;
import ua.com.alevel.persistence.type.RoleType;
import ua.com.alevel.util.SecurityUtil;
import ua.com.alevel.web.controller.AbstractController;
import ua.com.alevel.web.dto.response.user.UserResponseDto;

@Validated
@Controller
@RequestMapping("/account")
public class PersonalController extends AbstractController {

    private final UserFacade userFacade;

    public PersonalController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @GetMapping
    public String get(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        UserResponseDto user = userFacade.findByEmail(username);
        model.addAttribute("user", user);
        if (SecurityUtil.hasRole(RoleType.ROLE_ADMIN.name())) {
            model.addAttribute("showAdmin", true);
        }
        if (SecurityUtil.hasRole(RoleType.ROLE_DOCTOR.name()) || SecurityUtil.hasRole(RoleType.ROLE_PATIENT_DOCTOR.name())) {
            model.addAttribute("showDoctor", true);
        }
        if (SecurityUtil.hasRole(RoleType.ROLE_PATIENT.name()) || SecurityUtil.hasRole(RoleType.ROLE_PATIENT_DOCTOR.name())) {
            model.addAttribute("showPatient", true);
        }
        return "pages/personal/account";
    }

    @GetMapping("/add")
    public String add(Model model) {
        if (SecurityUtil.hasRole(RoleType.ROLE_ADMIN.name()) || SecurityUtil.hasRole(RoleType.ROLE_DELETED.name()) || SecurityUtil.hasRole(RoleType.ROLE_PATIENT_DOCTOR.name())) {
            model.addAttribute("showPatient", false);
            model.addAttribute("showDoctor", false);
            model.addAttribute("showError", false);
        }
        if (SecurityUtil.hasRole(RoleType.ROLE_PATIENT.name())) {
            model.addAttribute("showPatient", false);
            model.addAttribute("showDoctor", true);
        }
        if (SecurityUtil.hasRole(RoleType.ROLE_DOCTOR.name())) {
            model.addAttribute("showPatient", true);
            model.addAttribute("showDoctor", false);
        }
        if (SecurityUtil.hasRole(RoleType.ROLE_PERSONAL.name())) {
            model.addAttribute("showPatient", true);
            model.addAttribute("showDoctor", true);
        }
        return "pages/personal/add";
    }

    @PostMapping("/delete")
    public String delete() {
        UserResponseDto userResponseDto = userFacade.findByEmail(SecurityUtil.getUsername());
        userFacade.delete(userResponseDto.getId());
        return "redirect:/logout";
    }

}
