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
import ua.com.alevel.hw_10_web_repository.facade.PatientFacade;
import ua.com.alevel.hw_10_web_repository.facade.ReceptionFacade;
import ua.com.alevel.hw_10_web_repository.dto.impl.reception.ReceptionFullResponseDto;
import ua.com.alevel.hw_10_web_repository.dto.impl.reception.ReceptionRequestDto;
import ua.com.alevel.hw_10_web_repository.dto.impl.reception.ReceptionResponseDto;
import ua.com.alevel.hw_10_web_repository.facade.DoctorFacade;
import ua.com.alevel.hw_10_web_repository.view.PageData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static ua.com.alevel.hw_10_web_repository.util.WebRequestUtil.DEFAULT_ORDER_PARAM_VALUE;

@Controller
@RequestMapping("/receptions")
public class ReceptionController extends AbstractController {

    private final ReceptionFacade receptionFacade;
    private final DoctorFacade doctorFacade;
    private final PatientFacade patientFacade;

    public ReceptionController(ReceptionFacade receptionFacade, DoctorFacade doctorFacade, PatientFacade patientFacade) {
        this.receptionFacade = receptionFacade;
        this.doctorFacade = doctorFacade;
        this.patientFacade = patientFacade;
    }


    @GetMapping("/patient_receptions/{id}")
    public String findAllByPatient(@PathVariable Long id, Model model, WebRequest webRequest) {
        HeaderName[] columnNames = new HeaderName[]{
                new HeaderName("#", null, null),
                new HeaderName("Date and Time", "cast(date_of_reception as datetime) + cast(reception_time as datetime)", "cast(date_of_reception as datetime) + cast(reception_time as datetime)"),
                new HeaderName("Doctor", "d.name", "d.name"),
                new HeaderName("Details", null, null),
                new HeaderName("Update", null, null)
        };
        PageData<ReceptionResponseDto> response = receptionFacade.findAllByPatientId(webRequest, id);
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
        model.addAttribute("createUrl", "/receptions/patient/get/" + id);
        model.addAttribute("pageData", response);
        model.addAttribute("id", id);
        model.addAttribute("cardHeader", "Receptions");
        return "receptions/patient_receptions";
    }

    @GetMapping("/")
    public String findAll(Model model, WebRequest webRequest) {
        HeaderName[] columnNames = new HeaderName[]{
                new HeaderName("#", null, null),
                new HeaderName("Date and Time", "cast(date_of_reception as datetime) + cast(reception_time as datetime)", "cast(date_of_reception as datetime) + cast(reception_time as datetime)"),
                new HeaderName("Doctor", "d.name", "d.name"),
                new HeaderName("Patient", "p.name", "p.name"),
                new HeaderName("Details", null, null),
                new HeaderName("Update", null, null)
        };
        PageData<ReceptionResponseDto> response = receptionFacade.findAll(webRequest);
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
        model.addAttribute("createUrl", "/receptions/get/");
        model.addAttribute("pageData", response);
        model.addAttribute("cardHeader", "Receptions");
        return "receptions/receptions_all";
    }

    @GetMapping("/doctor_receptions/{id}")
    public String findAllByDoctor(@PathVariable Long id, Model model, WebRequest webRequest) {
        HeaderName[] columnNames = new HeaderName[]{
                new HeaderName("#", null, null),
                new HeaderName("Date and Time", "cast(date_of_reception as datetime) + cast(reception_time as datetime)", "cast(date_of_reception as datetime) + cast(reception_time as datetime)"),
                new HeaderName("Patient", "p.name", "p.name"),
                new HeaderName("Details", null, null),
                new HeaderName("Update", null, null)
        };
        PageData<ReceptionResponseDto> response = receptionFacade.findAllByDoctorId(webRequest, id);
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
        model.addAttribute("createUrl", "/receptions/doctor/get/" + id);
        model.addAttribute("id", id);
        model.addAttribute("pageData", response);
        model.addAttribute("cardHeader", "Receptions");
        return "receptions/doctor_receptions";
    }

    @PostMapping("/doctor/get/{id}")
    public ModelAndView findAllByDoctorRedirect(@PathVariable Long id, WebRequest request, ModelMap model) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach(model::addAttribute);
        }
        return new ModelAndView("redirect:/receptions/doctor_receptions/" + id, model);
    }

    @PostMapping("/patient/get/{id}")
    public ModelAndView findAllByPatientRedirect(@PathVariable Long id, WebRequest request, ModelMap model) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach(model::addAttribute);
        }
        return new ModelAndView("redirect:/receptions/patient_receptions/" + id, model);
    }

    @PostMapping("/get")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach(model::addAttribute);
        }
        return new ModelAndView("redirect:/receptions/", model);
    }

    //findById
    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        ReceptionFullResponseDto doctor = receptionFacade.findById(id);
        model.addAttribute("reception", doctor);
        model.addAttribute("doctor", doctor.getDoctor());
        model.addAttribute("patient", doctor.getPatient());
        return "receptions/eceptions_details";
    }

    //delete
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        receptionFacade.delete(id);
        return "redirect:/receptions/";
    }

    //create
    @GetMapping("/new")
    public String redirectToNewReceptionPage(Model model) {
        model.addAttribute("doctors", doctorFacade.findAll());
        model.addAttribute("patients", patientFacade.findAll());
        return "receptions/reception_new";
    }

    //create
    @PostMapping("/create")
    public String createNewReception(ReceptionRequestDto dto) {
        receptionFacade.create(dto);
        return "redirect:/receptions/";
    }

    //update
    @GetMapping("/update/{id}")
    public String redirectToUpdateReceptionPage(Model model, @PathVariable Long id) {
        ReceptionFullResponseDto dto = receptionFacade.findById(id);
        model.addAttribute("reception", dto);
        return "receptions/reception_update";
    }

    //update
    @PostMapping("/updated/{id}")
    public String updatePatient(ReceptionRequestDto dto, @PathVariable Long id) {
        receptionFacade.update(dto, id);
        return "redirect:/receptions/";
    }
}
