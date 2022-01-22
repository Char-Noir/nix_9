package ua.com.alevel.hw_8_web_jdbc.controller;

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
import ua.com.alevel.hw_8_web_jdbc.dto.impl.doctor.DoctorFullResponseDto;
import ua.com.alevel.hw_8_web_jdbc.dto.impl.patient.PatientFullResponseDto;
import ua.com.alevel.hw_8_web_jdbc.dto.impl.patient.PatientRequestDto;
import ua.com.alevel.hw_8_web_jdbc.dto.impl.patient.PatientResponseDto;
import ua.com.alevel.hw_8_web_jdbc.facade.DoctorFacade;
import ua.com.alevel.hw_8_web_jdbc.facade.PatientFacade;
import ua.com.alevel.hw_8_web_jdbc.view.PageData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static ua.com.alevel.hw_8_web_jdbc.util.WebRequestUtil.DEFAULT_ORDER_PARAM_VALUE;

@Controller
@RequestMapping("/patients")
public class PatientController extends AbstractController {

    private final PatientFacade patientFacade;
    private final DoctorFacade doctorFacade;

    public PatientController(PatientFacade patientFacade, DoctorFacade doctorFacade) {
        this.patientFacade = patientFacade;
        this.doctorFacade = doctorFacade;
    }

    //findAll
    @GetMapping
    public String findAll(Model model, WebRequest webRequest) {
        HeaderName[] columnNames = new HeaderName[]{
                new HeaderName("#", null, null),
                new HeaderName("Name", "name", "name"),
                new HeaderName("Date Of Birth", "dateOfBirth", "date_of_Birth"),
                new HeaderName("Phone Number", "phoneNumber", "phone_number"),
                new HeaderName("Status", "status", "status_name"),
                new HeaderName("Receptions count", "receptCount", "count(distinct r.id_reception)"),
                new HeaderName("Details", null, null),
                new HeaderName("Delete", null, null)
        };
        PageData<PatientResponseDto> response = patientFacade.findAll(webRequest);
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
        model.addAttribute("createUrl", "/patients/all");
        model.addAttribute("pageData", response);
        model.addAttribute("cardHeader", "All Patients");
        return "patients/patients_all";
    }


    //findAll
    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach(model::addAttribute);
        }
        return new ModelAndView("redirect:/patients", model);
    }

    //create
    @GetMapping("/new")
    public String redirectToNewPatientPage(Model model) {
        return "patients/patient_new";
    }

    //create
    @PostMapping("/create")
    public String createNewPatient(PatientRequestDto dto) {
        patientFacade.create(dto);
        return "redirect:/patients";
    }

    //update
    @GetMapping("/update/{id}")
    public String redirectToUpdatePatientPage(Model model, @PathVariable Long id) {
        PatientFullResponseDto dto = patientFacade.findById(id);
        model.addAttribute("patient", dto);
        return "patients/patient_update";
    }

    //update
    @PostMapping("/updated/{id}")
    public String updatePatient(PatientRequestDto dto, @PathVariable Long id) {
        patientFacade.update(dto, id);
        return "redirect:/patients";
    }

    //findById
    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        PatientFullResponseDto dto = patientFacade.findById(id);
        DoctorFullResponseDto doctor = doctorFacade.findDoctorByPatientId(id);
        model.addAttribute("patient", dto);
        model.addAttribute("doctor", doctor);
        System.out.println("doctor = " + doctor);
        return "patients/patient_details";
    }

    //delete
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {
        patientFacade.delete(id);
        return "redirect:/patients";
    }


}
