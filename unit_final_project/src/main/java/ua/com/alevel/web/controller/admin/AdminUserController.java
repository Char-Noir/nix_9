package ua.com.alevel.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.facade.DoctorFacade;
import ua.com.alevel.facade.PatientFacade;
import ua.com.alevel.facade.UserFacade;
import ua.com.alevel.validated.ValidId;
import ua.com.alevel.web.controller.AbstractController;
import ua.com.alevel.web.dto.request.user.UserRequestDto;
import ua.com.alevel.web.dto.response.PageData;
import ua.com.alevel.web.dto.response.user.UserResponseDto;

import java.util.Objects;

import static ua.com.alevel.web.controller.ControllerUtils.USER_COLUMNS;

@Controller
@RequestMapping("/admin/user")
public class AdminUserController extends AbstractController {

    private final UserFacade userFacade;
    private final DoctorFacade doctorFacade;
    private final PatientFacade patientFacade;

    public AdminUserController(UserFacade userFacade, DoctorFacade doctorFacade, PatientFacade patientFacade) {
        this.userFacade = userFacade;
        this.doctorFacade = doctorFacade;
        this.patientFacade = patientFacade;
    }

    @GetMapping("/s")
    public String findUsers(Model model, WebRequest webRequest) {
        PageData<UserResponseDto> response = userFacade.findAll(webRequest);
        initDataTable(response, USER_COLUMNS, model);
        model.addAttribute("createUrl", "/admin/user/s/all");
        model.addAttribute("nextUrl", "/admin/user/details/");
        model.addAttribute("cardHeader", "All Users");
        return "pages/admin/users/all";
    }

    @PostMapping("s/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        return findAllRedirect(request, model, "admin/user/s");
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable @ValidId Long id, Model model) {
        UserResponseDto dto = userFacade.findById(id);
        model.addAttribute("user", dto);
        if (Objects.equals(dto.getRole(), "doctor") || Objects.equals(dto.getRole(), "patient_doctor")) {
            model.addAttribute("doctorId", doctorFacade.findByUserId(dto.getId()).getId());
        }
        if (Objects.equals(dto.getRole(), "patient") || Objects.equals(dto.getRole(), "patient_doctor")) {
            model.addAttribute("patientId", patientFacade.findByUserId(dto.getId()).getId());
        }
        return "pages/admin/users/details";
    }


    @GetMapping("/edit/{id}")
    public String update(Model model, @PathVariable @ValidId Long id) {
        UserResponseDto user = userFacade.findById(id);
        System.out.println(user);
        model.addAttribute("user", new UserRequestDto());
        model.addAttribute("userOld", user);
        return "pages/admin/users/update";
    }

    @PostMapping("/edit/post/{id}")
    public String updatePost(@ModelAttribute(name = "user") UserRequestDto dto, @PathVariable @ValidId Long id) {
        UserResponseDto user = userFacade.findById(id);
        System.out.println(user);
        userFacade.update(dto, id);
        return "redirect:/admin/user/details/" + id;
    }

    @PostMapping("/delete/{id}")
    public String deletePost(@PathVariable @ValidId Long id) {
        userFacade.delete(id);
        return "redirect:/admin/user/details/" + id;
    }


}
