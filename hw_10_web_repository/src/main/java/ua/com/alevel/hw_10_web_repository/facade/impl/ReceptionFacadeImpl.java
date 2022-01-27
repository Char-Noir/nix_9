package ua.com.alevel.hw_10_web_repository.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.hw_10_web_repository.facade.ReceptionFacade;
import ua.com.alevel.hw_10_web_repository.persistence.datatable.DataTableRequest;
import ua.com.alevel.hw_10_web_repository.persistence.datatable.DataTableResponse;
import ua.com.alevel.hw_10_web_repository.persistence.entity.Reception;
import ua.com.alevel.hw_10_web_repository.service.DoctorService;
import ua.com.alevel.hw_10_web_repository.service.PatientService;
import ua.com.alevel.hw_10_web_repository.service.ReceptionService;
import ua.com.alevel.hw_10_web_repository.util.WebRequestUtil;
import ua.com.alevel.hw_10_web_repository.view.PageAndSizeData;
import ua.com.alevel.hw_10_web_repository.view.PageData;
import ua.com.alevel.hw_10_web_repository.view.SortData;
import ua.com.alevel.hw_10_web_repository.dto.impl.reception.ReceptionFullResponseDto;
import ua.com.alevel.hw_10_web_repository.dto.impl.reception.ReceptionRequestDto;
import ua.com.alevel.hw_10_web_repository.dto.impl.reception.ReceptionResponseDto;

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
