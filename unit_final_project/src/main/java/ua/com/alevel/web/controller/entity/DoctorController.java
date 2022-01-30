package ua.com.alevel.web.controller.entity;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.facade.DoctorFacade;
import ua.com.alevel.facade.EnumFacade;
import ua.com.alevel.facade.UserFacade;
import ua.com.alevel.persistence.entity.doctor.Category;
import ua.com.alevel.persistence.entity.doctor.Specialization;
import ua.com.alevel.persistence.type.RoleType;
import ua.com.alevel.util.SecurityUtil;
import ua.com.alevel.validated.ValidId;
import ua.com.alevel.web.controller.AbstractController;
import ua.com.alevel.web.dto.request.doctor.DoctorRequestDto;
import ua.com.alevel.web.dto.response.PageData;
import ua.com.alevel.web.dto.response.doctor.DoctorResponseDto;
import ua.com.alevel.web.dto.response.user.UserResponseDto;

import java.util.Objects;

import static ua.com.alevel.web.controller.ControllerUtils.DOCTOR_COLUMNS;

@Validated
@Controller
@RequestMapping("/doctor")
public class DoctorController extends AbstractController {

    private final DoctorFacade doctorFacade;
    private final UserFacade userFacade;
    private final EnumFacade<Category> categoryEnumFacade;
    private final EnumFacade<Specialization> specializationEnumFacadeEnumFacade;


    public DoctorController(DoctorFacade doctorFacade, UserFacade userFacade, EnumFacade<Category> categoryEnumFacade, EnumFacade<Specialization> specializationEnumFacadeEnumFacade) {
        this.doctorFacade = doctorFacade;
        this.userFacade = userFacade;
        this.categoryEnumFacade = categoryEnumFacade;
        this.specializationEnumFacadeEnumFacade = specializationEnumFacadeEnumFacade;
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("doctor", new DoctorRequestDto());
        model.addAttribute("categories", categoryEnumFacade.findAll(Category.class));
        model.addAttribute("specializations", specializationEnumFacadeEnumFacade.findAll(Specialization.class));
        return "pages/doctors/add";
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
        DoctorResponseDto doctor = doctorFacade.findByUserId(user.getId());
        model.addAttribute("doctor", new DoctorRequestDto());
        model.addAttribute("doctorOld", doctor);
        model.addAttribute("categories", categoryEnumFacade.findAll(Category.class));
        model.addAttribute("specializations", specializationEnumFacadeEnumFacade.findAll(Specialization.class));
        return "pages/doctors/update";
    }

    @PostMapping("/edit/post")
    public String updatePost(@ModelAttribute(name = "doctor") DoctorRequestDto dto) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        System.out.println(dto);
        UserResponseDto user = userFacade.findByEmail(username);
        dto.setUser(user.getId());
        DoctorResponseDto doctor = doctorFacade.findByUserId(user.getId());
        doctorFacade.update(dto, doctor.getId());
        return "redirect:/account/doctor/";
    }

    @PostMapping("/add/post")
    public String addPost(@ModelAttribute DoctorRequestDto dto) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        System.out.println(dto);
        UserResponseDto user = userFacade.findByEmail(username);
        dto.setUser(user.getId());
        doctorFacade.create(dto);
        return "redirect:/logout";
    }

    @GetMapping("s")
    public String findAll(Model model, WebRequest webRequest) {
        PageData<DoctorResponseDto> response = doctorFacade.findAll(webRequest);
        initDataTable(response, DOCTOR_COLUMNS, model);
        model.addAttribute("createUrl", "/doctor/s/all");
        model.addAttribute("nextUrl", "/doctor/details/");
        model.addAttribute("cardHeader", "All Doctors");
        return "pages/doctors/all";
    }

    @PostMapping("s/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        return findAllRedirect(request, model, "doctor/s");
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable @ValidId Long id, Model model) {
        DoctorResponseDto dto = doctorFacade.findById(id);
        if (!SecurityUtil.hasRole(RoleType.ROLE_ADMIN.name()) && Objects.equals(dto.getUser().getRole(), "deleted")) {
            return "redirect:/doctor/s";
        }
        model.addAttribute("doctor", dto);
        return "pages/doctors/details";
    }
}
