package ua.com.alevel.hw_10_web_repository.controller;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.hw_10_web_repository.dto.impl.doctor.DoctorFullResponseDto;
import ua.com.alevel.hw_10_web_repository.dto.impl.doctor.DoctorRequestDto;
import ua.com.alevel.hw_10_web_repository.dto.impl.doctor.DoctorResponseDto;
import ua.com.alevel.hw_10_web_repository.facade.DoctorFacade;
import ua.com.alevel.hw_10_web_repository.persistence.entity.Category;
import ua.com.alevel.hw_10_web_repository.persistence.entity.Specialization;
import ua.com.alevel.hw_10_web_repository.view.PageData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static ua.com.alevel.hw_10_web_repository.util.WebRequestUtil.DEFAULT_ORDER_PARAM_VALUE;

@Controller
@RequestMapping("/doctors")
public class DoctorController extends AbstractController {

    private final DoctorFacade doctorFacade;

    public DoctorController(DoctorFacade doctorFacade) {
        this.doctorFacade = doctorFacade;
    }

    //findAll
    @GetMapping
    public String findAll(Model model, WebRequest webRequest) {
        HeaderName[] columnNames = new HeaderName[]{
                new HeaderName("#", null, null),
                new HeaderName("Name", "name", "name"),
                new HeaderName("Date Of Certification", "dateOfCertification", "date_of_certification"),
                new HeaderName("Category", "category", "id_category"),
                new HeaderName("Specialization", "specialization", "id_specializations"),
                new HeaderName("Status", "status", "status_name"),
                new HeaderName("Receptions count", "receptCount", "count(distinct r.id_reception)"),
                new HeaderName("Details", null, null),
                new HeaderName("Delete", null, null)
        };
        PageData<DoctorResponseDto> response = doctorFacade.findAll(webRequest);
        response.initPaginationState(response.getCurrentPage());
        List<HeaderData> headerDataList = new ArrayList<>();
        for (HeaderName headerName : columnNames) {
            HeaderData data = new HeaderData();
            data.setHeaderName(headerName.getColumnName());
            if (StringUtils.isBlank(headerName.getTableName())) {
                data.setSortable(false);
            } else {
                data.setSortable(true);
                data.setSort(headerName.getDbName());
                System.out.println(response.getSort());
                if (response.getSort().equals(headerName.getDbName())) {
                    System.out.println(response.getOrder());
                    data.setActive(true);
                    data.setOrder(response.getOrder());
                } else {
                    data.setActive(false);
                    data.setOrder(DEFAULT_ORDER_PARAM_VALUE);
                }
            }
            headerDataList.add(data);
        }

        model.addAttribute("headerDataList", headerDataList);
        model.addAttribute("createUrl", "/doctors/all");
        model.addAttribute("pageData", response);
        model.addAttribute("cardHeader", "All Doctors");
        return "doctors/doctors_all";
    }

    //findAll
    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach(model::addAttribute);
        }
        return new ModelAndView("redirect:/doctors", model);
    }

    //create
    @GetMapping("/new")
    public String redirectToNewDoctorPage(Model model) {
        model.addAttribute("categories", Category.values());
        model.addAttribute("specializations", Specialization.values());
        return "doctors/doctor_new";
    }

    //create
    @PostMapping("/create")
    public String createNewDoctor(DoctorRequestDto dto) {
        doctorFacade.create(dto);
        return "redirect:/doctors";
    }

    //update
    @GetMapping("/update/{id}")
    public String redirectToUpdateDoctorPage(Model model, @PathVariable Long id) {
        DoctorFullResponseDto dto = doctorFacade.findById(id);
        model.addAttribute("doctor", dto);
        model.addAttribute("categories", Category.values());
        model.addAttribute("specializations", Specialization.values());
        return "doctors/doctor_update";
    }

    //update
    @PostMapping("/updated/{id}")
    public String updateDoctor(DoctorRequestDto dto, @PathVariable Long id) {
        doctorFacade.update(dto, id);
        return "redirect:/doctors";
    }

    //findById
    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        DoctorFullResponseDto doctor = doctorFacade.findById(id);
        model.addAttribute("doctor", doctor);
        System.out.println("doctor = " + doctor);
        return "doctors/doctor_details";
    }

    //delete
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        doctorFacade.delete(id);
        return "redirect:/doctors";
    }


}
