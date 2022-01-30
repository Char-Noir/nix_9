package ua.com.alevel.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.facade.PatientFacade;
import ua.com.alevel.facade.UserFacade;
import ua.com.alevel.validated.ValidId;
import ua.com.alevel.web.controller.AbstractController;
import ua.com.alevel.web.dto.request.patient.PatientRequestDto;
import ua.com.alevel.web.dto.response.PageData;
import ua.com.alevel.web.dto.response.patient.PatientResponseDto;
import ua.com.alevel.web.dto.response.user.UserResponseDto;

import static ua.com.alevel.web.controller.ControllerUtils.PATIENT_COLUMNS;

@Controller
@RequestMapping("/admin/patient")
public class AdminPatientController extends AbstractController {


    private final PatientFacade patientFacade;
    private final UserFacade userFacade;


    public AdminPatientController(PatientFacade patientFacade, UserFacade userFacade) {
        this.patientFacade = patientFacade;
        this.userFacade = userFacade;
    }

    @GetMapping("/s")
    public String findPatients(Model model, WebRequest webRequest) {
        PageData<PatientResponseDto> response = patientFacade.findAll(webRequest);
        initDataTable(response, PATIENT_COLUMNS, model);
        model.addAttribute("createUrl", "/admin/patient/s/all");
        model.addAttribute("nextUrl", "/admin/patient/details/");
        model.addAttribute("cardHeader", "All Patients");
        return "pages/admin/patients/all";
    }

    @PostMapping("s/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        return findAllRedirect(request, model, "patient/s");
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable @ValidId Long id, Model model) {
        PatientResponseDto dto = patientFacade.findById(id);
        model.addAttribute("patient", dto);
        return "pages/admin/patients/details";
    }

    @GetMapping("/add/{id}")
    public String add(Model model, @PathVariable @ValidId Long id) {
        UserResponseDto user = userFacade.findById(id);
        if (user == null || !user.getVisible()) {
            return "redirect:/admin/patient/s/";
        }
        model.addAttribute("patient", new PatientRequestDto());
        return "pages/admin/patients/add";
    }

    @GetMapping("/edit/{id}")
    public String update(Model model, @PathVariable @ValidId Long id) {
        PatientResponseDto patient = patientFacade.findById(id);
        model.addAttribute("patient", new PatientRequestDto());
        model.addAttribute("patientOld", patient);
        return "pages/admin/patients/update";
    }

    @PostMapping("/edit/post/{id}")
    public String updatePost(@ModelAttribute(name = "patient") PatientRequestDto dto, @PathVariable @ValidId Long id) {
        PatientResponseDto patient = patientFacade.findById(id);
        dto.setUserId(patient.getUser().getId());
        patientFacade.update(dto, patient.getId());
        return "redirect:/admin/patient/details/" + id;
    }

    @PostMapping("/add/post/{id}")
    public String addPost(@ModelAttribute PatientRequestDto dto, @PathVariable @ValidId Long id) {
        UserResponseDto user = userFacade.findById(id);
        if (user == null || !user.getVisible()) {
            return "redirect:/admin/patient/s/";
        }
        dto.setUserId(user.getId());
        patientFacade.create(dto);
        return "redirect:/admin/user/details/" + id;
    }


}
