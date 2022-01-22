package ua.com.alevel.hw_8_web_jdbc.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.hw_8_web_jdbc.dto.impl.patient.PatientFullResponseDto;
import ua.com.alevel.hw_8_web_jdbc.dto.impl.patient.PatientRequestDto;
import ua.com.alevel.hw_8_web_jdbc.dto.impl.patient.PatientResponseDto;
import ua.com.alevel.hw_8_web_jdbc.facade.PatientFacade;
import ua.com.alevel.hw_8_web_jdbc.persistence.datatable.DataTableRequest;
import ua.com.alevel.hw_8_web_jdbc.persistence.datatable.DataTableResponse;
import ua.com.alevel.hw_8_web_jdbc.persistence.entity.Patient;
import ua.com.alevel.hw_8_web_jdbc.service.PatientService;
import ua.com.alevel.hw_8_web_jdbc.util.WebRequestUtil;
import ua.com.alevel.hw_8_web_jdbc.view.PageAndSizeData;
import ua.com.alevel.hw_8_web_jdbc.view.PageData;
import ua.com.alevel.hw_8_web_jdbc.view.SortData;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientFacadeImpl implements PatientFacade {

    PatientService patientService;

    PatientFacadeImpl(PatientService patientService) {
        this.patientService = patientService;
    }

    @Override
    public void create(PatientRequestDto patientRequestDto) {
        patientService.create(patientRequestDto.getPatient());
    }

    @Override
    public void update(PatientRequestDto patientRequestDto, Long id) {
        Patient entity = patientRequestDto.getPatient();
        entity.setId(id);
        patientService.update(entity);
    }

    @Override
    public void delete(Long id) {
        patientService.delete(id);
    }

    @Override
    public PatientFullResponseDto findById(Long id) {
        Patient patient = patientService.findById(id);
        return new PatientFullResponseDto(patient);
    }

    @Override
    public boolean exists(Long id) {
        return patientService.exists(id);
    }

    @Override
    public PageData<PatientResponseDto> findAll(WebRequest request) {
        PageAndSizeData pageAndSizeData = WebRequestUtil.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtil.generateSortData(request, "p.id_patient");
        DataTableRequest dataTableRequest = new DataTableRequest();
        dataTableRequest.setPageSize(pageAndSizeData.getSize());
        dataTableRequest.setCurrentPage(pageAndSizeData.getPage());
        dataTableRequest.setSort(sortData.getSort());
        dataTableRequest.setOrder(sortData.getOrder());

        DataTableResponse<Patient> all = patientService.findAll(dataTableRequest);
        List<PatientResponseDto> list = all.getItems().
                stream().
                map(PatientResponseDto::new).
                peek(dto -> dto.setRecCount((Integer) all.getOtherParamMap().get(dto.getId()))).
                collect(Collectors.toList());

        PageData<PatientResponseDto> pageData = new PageData<>();
        pageData.setItems(list);
        pageData.setCurrentPage(pageAndSizeData.getPage());
        pageData.setPageSize(pageAndSizeData.getSize());
        pageData.setOrder(sortData.getOrder());
        pageData.setSort(sortData.getSort());
        pageData.setItemsSize(all.getItemsSize());
        pageData.setCurrentShowFromEntries(((long) (pageAndSizeData.getPage() - 1) * pageData.getPageSize() + 1));
        pageData.setCurrentShowToEntries(Math.min(((long) (pageAndSizeData.getPage()) * pageData.getPageSize()), all.getItemsSize()));
        pageData.setTotalPageSize((int) Math.ceil(((double) all.getItemsSize() / (double)pageData.getPageSize())));
        pageData.initPaginationState(pageData.getCurrentPage());

        System.out.println("pageData = " + pageData);
        return pageData;
    }

    @Override
    public PageData<PatientResponseDto> findAll() {
        PageAndSizeData pageAndSizeData = new PageAndSizeData(1, -1);
        SortData sortData = new SortData("p.id_patient", "asc");
        DataTableRequest dataTableRequest = new DataTableRequest();
        dataTableRequest.setPageSize(pageAndSizeData.getSize());
        dataTableRequest.setCurrentPage(pageAndSizeData.getPage());
        dataTableRequest.setSort(sortData.getSort());
        dataTableRequest.setOrder(sortData.getOrder());

        DataTableResponse<Patient> all = patientService.findAll(dataTableRequest);
        List<PatientResponseDto> list = all.getItems().
                stream().
                map(PatientResponseDto::new).
                peek(dto -> dto.setRecCount((Integer) all.getOtherParamMap().get(dto.getId()))).
                collect(Collectors.toList());

        PageData<PatientResponseDto> pageData = new PageData<>();
        pageData.setItems(list);
        pageData.setCurrentPage(pageAndSizeData.getPage());
        pageData.setPageSize(pageAndSizeData.getSize());
        pageData.setOrder(sortData.getOrder());
        pageData.setSort(sortData.getSort());
        pageData.setItemsSize(all.getItemsSize());
        pageData.setCurrentShowFromEntries(((long) (pageAndSizeData.getPage() - 1) * pageData.getPageSize() + 1));
        pageData.setCurrentShowToEntries(Math.min(((long) (pageAndSizeData.getPage()) * pageData.getPageSize()), all.getItemsSize()));
        pageData.setTotalPageSize((int) Math.ceil(((double) all.getItemsSize() / (double)pageData.getPageSize())));
        pageData.initPaginationState(pageData.getCurrentPage());

        System.out.println("pageData = " + pageData);
        return pageData;
    }

    @Override
    public PageData<PatientResponseDto> findAllByDoctorId(WebRequest request, Long id) {
        PageAndSizeData pageAndSizeData = WebRequestUtil.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtil.generateSortData(request, "p.id_patient");
        DataTableRequest dataTableRequest = new DataTableRequest();
        dataTableRequest.setPageSize(pageAndSizeData.getSize());
        dataTableRequest.setCurrentPage(pageAndSizeData.getPage());
        dataTableRequest.setSort(sortData.getSort());
        dataTableRequest.setOrder(sortData.getOrder());

        DataTableResponse<Patient> all = patientService.findAllByDoctorId(dataTableRequest, id);
        List<PatientResponseDto> list = all.getItems().
                stream().
                map(PatientResponseDto::new).
                peek(dto -> dto.setRecCount((Integer) all.getOtherParamMap().get(dto.getId()))).
                collect(Collectors.toList());

        PageData<PatientResponseDto> pageData = new PageData<>();
        pageData.setItems(list);
        pageData.setCurrentPage(pageAndSizeData.getPage());
        pageData.setPageSize(pageAndSizeData.getSize());
        pageData.setOrder(sortData.getOrder());
        pageData.setSort(sortData.getSort());
        pageData.setItemsSize(all.getItemsSize());
        pageData.setCurrentShowFromEntries(((long) (pageAndSizeData.getPage() - 1) * pageData.getPageSize() + 1));
        pageData.setCurrentShowToEntries(Math.min(((long) (pageAndSizeData.getPage()) * pageData.getPageSize()), all.getItemsSize()));
        pageData.setTotalPageSize((int) Math.ceil(((double) all.getItemsSize() / (double)pageData.getPageSize())));
        pageData.initPaginationState(pageData.getCurrentPage());

        System.out.println("pageData = " + pageData);
        return pageData;
    }
}
