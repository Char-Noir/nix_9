package ua.com.alevel.hw_8_web_jdbc.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.hw_8_web_jdbc.dto.impl.doctor.DoctorFullResponseDto;
import ua.com.alevel.hw_8_web_jdbc.dto.impl.doctor.DoctorRequestDto;
import ua.com.alevel.hw_8_web_jdbc.dto.impl.doctor.DoctorResponseDto;
import ua.com.alevel.hw_8_web_jdbc.facade.DoctorFacade;
import ua.com.alevel.hw_8_web_jdbc.persistence.datatable.DataTableRequest;
import ua.com.alevel.hw_8_web_jdbc.persistence.datatable.DataTableResponse;
import ua.com.alevel.hw_8_web_jdbc.persistence.entity.Doctor;
import ua.com.alevel.hw_8_web_jdbc.service.DoctorService;
import ua.com.alevel.hw_8_web_jdbc.util.WebRequestUtil;
import ua.com.alevel.hw_8_web_jdbc.view.PageAndSizeData;
import ua.com.alevel.hw_8_web_jdbc.view.PageData;
import ua.com.alevel.hw_8_web_jdbc.view.SortData;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorFacadeImpl implements DoctorFacade {

    DoctorService doctorService;

    DoctorFacadeImpl(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @Override
    public void create(DoctorRequestDto doctorRequestDto) {
        doctorService.create(doctorRequestDto.getDoctor());
    }

    @Override
    public void update(DoctorRequestDto doctorRequestDto, Long id) {
        Doctor entity = doctorRequestDto.getDoctor();
        entity.setId(id);
        doctorService.update(entity);
    }

    @Override
    public void delete(Long id) {
        doctorService.delete(id);
    }

    @Override
    public DoctorFullResponseDto findById(Long id) {
        Doctor doctor = doctorService.findById(id);
        return new DoctorFullResponseDto(doctor);
    }

    @Override
    public boolean exists(Long id) {
        return doctorService.exists(id);
    }

    @Override
    public PageData<DoctorResponseDto> findAll(WebRequest request) {
        PageAndSizeData pageAndSizeData = WebRequestUtil.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtil.generateSortData(request, "d.id_doctor");
        DataTableRequest dataTableRequest = new DataTableRequest();
        dataTableRequest.setPageSize(pageAndSizeData.getSize());
        dataTableRequest.setCurrentPage(pageAndSizeData.getPage());
        dataTableRequest.setSort(sortData.getSort());
        dataTableRequest.setOrder(sortData.getOrder());

        DataTableResponse<Doctor> all = doctorService.findAll(dataTableRequest);
        List<DoctorResponseDto> list = all.getItems().
                stream().
                map(DoctorResponseDto::new).
                peek(dto -> dto.setRecCount((Integer) all.getOtherParamMap().get(dto.getId()))).
                collect(Collectors.toList());

        PageData<DoctorResponseDto> pageData = new PageData<>();
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
    public PageData<DoctorResponseDto> findAll() {
        PageAndSizeData pageAndSizeData = new PageAndSizeData(1, -1);
        SortData sortData = new SortData("d.id_doctor", "asc");
        DataTableRequest dataTableRequest = new DataTableRequest();
        dataTableRequest.setPageSize(pageAndSizeData.getSize());
        dataTableRequest.setCurrentPage(pageAndSizeData.getPage());
        dataTableRequest.setSort(sortData.getSort());
        dataTableRequest.setOrder(sortData.getOrder());

        DataTableResponse<Doctor> all = doctorService.findAll(dataTableRequest);
        List<DoctorResponseDto> list = all.getItems().
                stream().
                map(DoctorResponseDto::new).
                peek(dto -> dto.setRecCount((Integer) all.getOtherParamMap().get(dto.getId()))).
                collect(Collectors.toList());

        PageData<DoctorResponseDto> pageData = new PageData<>();
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
    public DoctorFullResponseDto findDoctorByPatientId(Long id) {
        Doctor doctor = doctorService.findDoctorByPatientId(id);
        return new DoctorFullResponseDto(doctor);
    }
}
