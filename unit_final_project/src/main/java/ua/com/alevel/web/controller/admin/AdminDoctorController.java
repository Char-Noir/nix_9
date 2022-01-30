package ua.com.alevel.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.facade.DoctorFacade;
import ua.com.alevel.facade.EnumFacade;
import ua.com.alevel.facade.UserFacade;
import ua.com.alevel.persistence.entity.doctor.Category;
import ua.com.alevel.persistence.entity.doctor.Specialization;
import ua.com.alevel.validated.ValidId;
import ua.com.alevel.web.controller.AbstractController;
import ua.com.alevel.web.dto.request.doctor.DoctorRequestDto;
import ua.com.alevel.web.dto.response.PageData;
import ua.com.alevel.web.dto.response.doctor.DoctorResponseDto;
import ua.com.alevel.web.dto.response.user.UserResponseDto;

import static ua.com.alevel.web.controller.ControllerUtils.DOCTOR_COLUMNS;

@Controller
@RequestMapping("/admin/doctor")
public class AdminDoctorController extends AbstractController {


    private final DoctorFacade doctorFacade;
    private final UserFacade userFacade;
    private final EnumFacade<Category> categoryEnumFacade;
    private final EnumFacade<Specialization> specializationEnumFacadeEnumFacade;

    public AdminDoctorController(DoctorFacade doctorFacade, UserFacade userFacade, EnumFacade<Category> categoryEnumFacade, EnumFacade<Specialization> specializationEnumFacadeEnumFacade) {
        this.doctorFacade = doctorFacade;
        this.userFacade = userFacade;
        this.categoryEnumFacade = categoryEnumFacade;
        this.specializationEnumFacadeEnumFacade = specializationEnumFacadeEnumFacade;
    }

    @GetMapping("/s")
    public String findDoctors(Model model, WebRequest webRequest) {
        PageData<DoctorResponseDto> response = doctorFacade.findAll(webRequest);
        initDataTable(response, DOCTOR_COLUMNS, model);
        model.addAttribute("createUrl", "/admin/doctor/s/all");
        model.addAttribute("nextUrl", "/admin/doctor/details/");
        model.addAttribute("cardHeader", "All Doctors");
        return "pages/admin/doctors/all";
    }

    @PostMapping("s/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        return findAllRedirect(request, model, "doctor/s");
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable @ValidId Long id, Model model) {
        DoctorResponseDto dto = doctorFacade.findById(id);
        model.addAttribute("doctor", dto);
        return "pages/admin/doctors/details";
    }

    @GetMapping("/add/{id}")
    public String add(Model model, @PathVariable @ValidId Long id) {
        UserResponseDto user = userFacade.findById(id);
        if (user == null || !user.getVisible()) {
            return "redirect:/admin/doctor/s/";
        }
        model.addAttribute("doctor", new DoctorRequestDto());
        model.addAttribute("categories", categoryEnumFacade.findAll(Category.class));
        model.addAttribute("specializations", specializationEnumFacadeEnumFacade.findAll(Specialization.class));
        return "pages/admin/doctors/add";
    }

    @GetMapping("/edit/{id}")
    public String update(Model model, @PathVariable @ValidId Long id) {
        DoctorResponseDto doctor = doctorFacade.findById(id);
        model.addAttribute("doctor", new DoctorRequestDto());
        model.addAttribute("doctorOld", doctor);
        model.addAttribute("categories", categoryEnumFacade.findAll(Category.class));
        model.addAttribute("specializations", specializationEnumFacadeEnumFacade.findAll(Specialization.class));
        return "pages/admin/doctors/update";
    }

    @PostMapping("/edit/post/{id}")
    public String updatePost(@ModelAttribute(name = "doctor") DoctorRequestDto dto, @PathVariable @ValidId Long id) {
        DoctorResponseDto doctor = doctorFacade.findById(id);
        dto.setUser(doctor.getUser().getId());
        doctorFacade.update(dto, doctor.getId());
        return "redirect:/admin/doctor/details/" + id;
    }

    @PostMapping("/add/post/{id}")
    public String addPost(@ModelAttribute DoctorRequestDto dto, @PathVariable @ValidId Long id) {
        UserResponseDto user = userFacade.findById(id);
        if (user == null || !user.getVisible()) {
            return "redirect:/admin/doctor/s/";
        }
        dto.setUser(user.getId());
        doctorFacade.create(dto);
        return "redirect:/admin/user/details/" + id;
    }


}
