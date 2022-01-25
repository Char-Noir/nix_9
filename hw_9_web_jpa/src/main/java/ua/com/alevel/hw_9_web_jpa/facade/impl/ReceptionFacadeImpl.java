package ua.com.alevel.hw_9_web_jpa.facade.impl;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.hw_9_web_jpa.dto.impl.reception.ReceptionFullResponseDto;
import ua.com.alevel.hw_9_web_jpa.dto.impl.reception.ReceptionRequestDto;
import ua.com.alevel.hw_9_web_jpa.dto.impl.reception.ReceptionResponseDto;
import ua.com.alevel.hw_9_web_jpa.facade.ReceptionFacade;
import ua.com.alevel.hw_9_web_jpa.persistence.datatable.DataTableRequest;
import ua.com.alevel.hw_9_web_jpa.persistence.datatable.DataTableResponse;
import ua.com.alevel.hw_9_web_jpa.persistence.entity.Doctor;
import ua.com.alevel.hw_9_web_jpa.persistence.entity.Patient;
import ua.com.alevel.hw_9_web_jpa.persistence.entity.Reception;
import ua.com.alevel.hw_9_web_jpa.service.DoctorService;
import ua.com.alevel.hw_9_web_jpa.service.PatientService;
import ua.com.alevel.hw_9_web_jpa.service.ReceptionService;
import ua.com.alevel.hw_9_web_jpa.util.WebRequestUtil;
import ua.com.alevel.hw_9_web_jpa.view.PageAndSizeData;
import ua.com.alevel.hw_9_web_jpa.view.PageData;
import ua.com.alevel.hw_9_web_jpa.view.SortData;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReceptionFacadeImpl implements ReceptionFacade {

    final ReceptionService receptionService;
    final DoctorService doctorService;
    final PatientService patientService;

    public ReceptionFacadeImpl(ReceptionService receptionService, DoctorService doctorService, PatientService patientService) {
        this.receptionService = receptionService;
        this.doctorService = doctorService;
        this.patientService = patientService;
    }

    @Override
    public void create(ReceptionRequestDto patientRequestDto) {
        receptionService.create(patientRequestDto.getReception());
    }

    @Override
    public void update(ReceptionRequestDto patientRequestDto, Long id) {
        Reception reception = receptionService.findById(id);
        Reception entity = patientRequestDto.getReception();
        reception.update(entity);
        receptionService.update(reception);
    }

    @Override
    public void delete(Long id) {
        receptionService.delete(id);
    }

    @Override
    public ReceptionFullResponseDto findById(Long id) {
        Reception reception = receptionService.findById(id);
        return new ReceptionFullResponseDto(reception);
    }

    @Override
    public boolean exists(Long id) {
        return doctorService.exists(id);
    }

    @Override
    public PageData<ReceptionResponseDto> findAll(WebRequest request) {
        PageAndSizeData pageAndSizeData = WebRequestUtil.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtil.generateSortData(request, "idReception");
        DataTableRequest dataTableRequest = new DataTableRequest();
        dataTableRequest.setPageSize(pageAndSizeData.getSize());
        dataTableRequest.setCurrentPage(pageAndSizeData.getPage());
        dataTableRequest.setSort(sortData.getSort());
        dataTableRequest.setOrder(sortData.getOrder());

        DataTableResponse<Reception> all = receptionService.findAll(dataTableRequest);
        List<ReceptionResponseDto> list = all.getItems().stream().map(ReceptionResponseDto::new).toList();

        PageData<ReceptionResponseDto> pageData = new PageData<>();
        pageData.setItems(list);
        pageData.setCurrentPage(pageAndSizeData.getPage());
        pageData.setPageSize(pageAndSizeData.getSize());
        pageData.setOrder(sortData.getOrder());
        pageData.setSort(sortData.getSort());
        pageData.setItemsSize(all.getItemsSize());
        pageData.setCurrentShowFromEntries(((long) (pageAndSizeData.getPage() - 1) * pageData.getPageSize() + 1));
        pageData.setCurrentShowToEntries(Math.min(((long) (pageAndSizeData.getPage()) * pageData.getPageSize()), all.getItemsSize()));
        pageData.setTotalPageSize((int) Math.ceil(((double) all.getItemsSize() / (double) pageData.getPageSize())));
        pageData.initPaginationState(pageData.getCurrentPage());

        System.out.println("pageData = " + pageData);
        return pageData;
    }

    @Override
    public PageData<ReceptionResponseDto> findAll() {
        PageAndSizeData pageAndSizeData = new PageAndSizeData(1, -1);
        SortData sortData = new SortData("idReception", "asc");
        DataTableRequest dataTableRequest = new DataTableRequest();
        dataTableRequest.setPageSize(pageAndSizeData.getSize());
        dataTableRequest.setCurrentPage(pageAndSizeData.getPage());
        dataTableRequest.setSort(sortData.getSort());
        dataTableRequest.setOrder(sortData.getOrder());

        DataTableResponse<Reception> all = receptionService.findAll(dataTableRequest);
        List<ReceptionResponseDto> list = all.getItems().stream().map(ReceptionResponseDto::new).toList();

        PageData<ReceptionResponseDto> pageData = new PageData<>();
        pageData.setItems(list);
        pageData.setCurrentPage(pageAndSizeData.getPage());
        pageData.setPageSize(pageAndSizeData.getSize());
        pageData.setOrder(sortData.getOrder());
        pageData.setSort(sortData.getSort());
        pageData.setItemsSize(all.getItemsSize());
        pageData.setCurrentShowFromEntries(((long) (pageAndSizeData.getPage() - 1) * pageData.getPageSize() + 1));
        pageData.setCurrentShowToEntries(Math.min(((long) (pageAndSizeData.getPage()) * pageData.getPageSize()), all.getItemsSize()));
        pageData.setTotalPageSize((int) Math.ceil(((double) all.getItemsSize() / (double) pageData.getPageSize())));
        pageData.initPaginationState(pageData.getCurrentPage());

        System.out.println("pageData = " + pageData);
        return pageData;
    }

    @Override
    public PageData<ReceptionResponseDto> findAllByPatientId(WebRequest request, Long id) {
        PageAndSizeData pageAndSizeData = WebRequestUtil.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtil.generateSortData(request, "idReception");
        DataTableRequest dataTableRequest = new DataTableRequest();
        dataTableRequest.setPageSize(pageAndSizeData.getSize());
        dataTableRequest.setCurrentPage(pageAndSizeData.getPage());
        dataTableRequest.setSort(sortData.getSort());
        dataTableRequest.setOrder(sortData.getOrder());

        DataTableResponse<Reception> all = receptionService.findAllByPatientId(dataTableRequest, id);
        List<ReceptionResponseDto> list = all.getItems().stream().map(ReceptionResponseDto::new).toList();


        PageData<ReceptionResponseDto> pageData = new PageData<>();
        pageData.setItems(list);
        pageData.setCurrentPage(pageAndSizeData.getPage());
        pageData.setPageSize(pageAndSizeData.getSize());
        pageData.setOrder(sortData.getOrder());
        pageData.setSort(sortData.getSort());
        pageData.setItemsSize(all.getItemsSize());
        pageData.setCurrentShowFromEntries(((long) (pageAndSizeData.getPage() - 1) * pageData.getPageSize() + 1));
        pageData.setCurrentShowToEntries(Math.min(((long) (pageAndSizeData.getPage()) * pageData.getPageSize()), all.getItemsSize()));
        pageData.setTotalPageSize((int) Math.ceil(((double) all.getItemsSize() / (double) pageData.getPageSize())));
        pageData.initPaginationState(pageData.getCurrentPage());

        System.out.println("pageData = " + pageData);
        return pageData;
    }

    @Override
    public PageData<ReceptionResponseDto> findAllByDoctorId(WebRequest request, Long id) {
        PageAndSizeData pageAndSizeData = WebRequestUtil.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtil.generateSortData(request, "idReception");
        DataTableRequest dataTableRequest = new DataTableRequest();
        dataTableRequest.setPageSize(pageAndSizeData.getSize());
        dataTableRequest.setCurrentPage(pageAndSizeData.getPage());
        dataTableRequest.setSort(sortData.getSort());
        dataTableRequest.setOrder(sortData.getOrder());

        DataTableResponse<Reception> all = receptionService.findAllByDoctorId(dataTableRequest, id);
        List<ReceptionResponseDto> list = all.getItems().stream().map(ReceptionResponseDto::new).toList();

        PageData<ReceptionResponseDto> pageData = new PageData<>();
        pageData.setItems(list);
        pageData.setCurrentPage(pageAndSizeData.getPage());
        pageData.setPageSize(pageAndSizeData.getSize());
        pageData.setOrder(sortData.getOrder());
        pageData.setSort(sortData.getSort());
        pageData.setItemsSize(all.getItemsSize());
        pageData.setCurrentShowFromEntries(((long) (pageAndSizeData.getPage() - 1) * pageData.getPageSize() + 1));
        pageData.setCurrentShowToEntries(Math.min(((long) (pageAndSizeData.getPage()) * pageData.getPageSize()), all.getItemsSize()));
        pageData.setTotalPageSize((int) Math.ceil(((double) all.getItemsSize() / (double) pageData.getPageSize())));
        pageData.initPaginationState(pageData.getCurrentPage());

        System.out.println("pageData = " + pageData);
        return pageData;
    }
}
