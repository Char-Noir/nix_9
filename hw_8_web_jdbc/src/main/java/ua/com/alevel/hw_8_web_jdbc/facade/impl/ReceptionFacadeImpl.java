package ua.com.alevel.hw_8_web_jdbc.facade.impl;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.hw_8_web_jdbc.dto.impl.doctor.DoctorFullResponseDto;
import ua.com.alevel.hw_8_web_jdbc.dto.impl.patient.PatientFullResponseDto;
import ua.com.alevel.hw_8_web_jdbc.dto.impl.reception.ReceptionFullResponseDto;
import ua.com.alevel.hw_8_web_jdbc.dto.impl.reception.ReceptionRequestDto;
import ua.com.alevel.hw_8_web_jdbc.dto.impl.reception.ReceptionResponseDto;
import ua.com.alevel.hw_8_web_jdbc.facade.ReceptionFacade;
import ua.com.alevel.hw_8_web_jdbc.persistence.datatable.DataTableRequest;
import ua.com.alevel.hw_8_web_jdbc.persistence.datatable.DataTableResponse;
import ua.com.alevel.hw_8_web_jdbc.persistence.entity.Doctor;
import ua.com.alevel.hw_8_web_jdbc.persistence.entity.Patient;
import ua.com.alevel.hw_8_web_jdbc.persistence.entity.Reception;
import ua.com.alevel.hw_8_web_jdbc.service.DoctorService;
import ua.com.alevel.hw_8_web_jdbc.service.PatientService;
import ua.com.alevel.hw_8_web_jdbc.service.ReceptionService;
import ua.com.alevel.hw_8_web_jdbc.util.WebRequestUtil;
import ua.com.alevel.hw_8_web_jdbc.view.PageAndSizeData;
import ua.com.alevel.hw_8_web_jdbc.view.PageData;
import ua.com.alevel.hw_8_web_jdbc.view.SortData;

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
        Reception entity = patientRequestDto.getReception();
        entity.setId(id);
        receptionService.update(entity);
    }

    @Override
    public void delete(Long id) {
        receptionService.delete(id);
    }

    @Override
    public ReceptionFullResponseDto findById(Long id) {
        Reception reception = receptionService.findById(id);
        Doctor doctor = doctorService.findById(reception.getDoctorId());
        Patient patient = patientService.findById(reception.getPatientId());
        return new ReceptionFullResponseDto(reception, new DoctorFullResponseDto(doctor), new PatientFullResponseDto(patient));
    }

    @Override
    public boolean exists(Long id) {
        return doctorService.exists(id);
    }

    @Override
    public PageData<ReceptionResponseDto> findAll(WebRequest request) {
        PageAndSizeData pageAndSizeData = WebRequestUtil.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtil.generateSortData(request, "r.id_reception");
        DataTableRequest dataTableRequest = new DataTableRequest();
        dataTableRequest.setPageSize(pageAndSizeData.getSize());
        dataTableRequest.setCurrentPage(pageAndSizeData.getPage());
        dataTableRequest.setSort(sortData.getSort());
        dataTableRequest.setOrder(sortData.getOrder());

        DataTableResponse<Reception> all = receptionService.findAll(dataTableRequest);
        List<ReceptionResponseDto> list = new ArrayList<>();
        for (Reception reception :
                all.getItems()) {
            Pair<Doctor, Patient> pair = (Pair<Doctor, Patient>) all.getOtherParamMap().get(reception.getId());
            ReceptionResponseDto receptionResponseDto = new ReceptionResponseDto(reception, pair.getLeft(), pair.getRight());
            list.add(receptionResponseDto);
        }

        PageData<ReceptionResponseDto> pageData = new PageData<>();
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
    public PageData<ReceptionResponseDto> findAll() {
        PageAndSizeData pageAndSizeData = new PageAndSizeData(1, -1);
        SortData sortData = new SortData("r.id_reception", "asc");
        DataTableRequest dataTableRequest = new DataTableRequest();
        dataTableRequest.setPageSize(pageAndSizeData.getSize());
        dataTableRequest.setCurrentPage(pageAndSizeData.getPage());
        dataTableRequest.setSort(sortData.getSort());
        dataTableRequest.setOrder(sortData.getOrder());

        DataTableResponse<Reception> all = receptionService.findAll(dataTableRequest);
        List<ReceptionResponseDto> list = new ArrayList<>();
        for (Reception reception :
                all.getItems()) {
            Pair<Doctor, Patient> pair = (Pair<Doctor, Patient>) all.getOtherParamMap().get(reception.getId());
            ReceptionResponseDto receptionResponseDto = new ReceptionResponseDto(reception, pair.getLeft(), pair.getRight());
            list.add(receptionResponseDto);
        }

        PageData<ReceptionResponseDto> pageData = new PageData<>();
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
    public PageData<ReceptionResponseDto> findAllByPatientId(WebRequest request, Long id) {
        PageAndSizeData pageAndSizeData = WebRequestUtil.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtil.generateSortData(request, "r.id_reception");
        DataTableRequest dataTableRequest = new DataTableRequest();
        dataTableRequest.setPageSize(pageAndSizeData.getSize());
        dataTableRequest.setCurrentPage(pageAndSizeData.getPage());
        dataTableRequest.setSort(sortData.getSort());
        dataTableRequest.setOrder(sortData.getOrder());

        DataTableResponse<Reception> all = receptionService.findAllByPatientId(dataTableRequest, id);
        List<ReceptionResponseDto> list = new ArrayList<>();
        for (Reception reception :
                all.getItems()) {
            Pair<Doctor, Patient> pair = (Pair<Doctor, Patient>) all.getOtherParamMap().get(reception.getId());
            ReceptionResponseDto receptionResponseDto = new ReceptionResponseDto(reception, pair.getLeft(), pair.getRight());
            list.add(receptionResponseDto);
        }

        PageData<ReceptionResponseDto> pageData = new PageData<>();
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
    public PageData<ReceptionResponseDto> findAllByDoctorId(WebRequest request, Long id) {
        PageAndSizeData pageAndSizeData = WebRequestUtil.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtil.generateSortData(request, "r.id_reception");
        DataTableRequest dataTableRequest = new DataTableRequest();
        dataTableRequest.setPageSize(pageAndSizeData.getSize());
        dataTableRequest.setCurrentPage(pageAndSizeData.getPage());
        dataTableRequest.setSort(sortData.getSort());
        dataTableRequest.setOrder(sortData.getOrder());

        DataTableResponse<Reception> all = receptionService.findAllByDoctorId(dataTableRequest, id);
        List<ReceptionResponseDto> list = new ArrayList<>();
        for (Reception reception :
                all.getItems()) {
            Pair<Doctor, Patient> pair = (Pair<Doctor, Patient>) all.getOtherParamMap().get(reception.getId());
            ReceptionResponseDto receptionResponseDto = new ReceptionResponseDto(reception, pair.getLeft(), pair.getRight());
            list.add(receptionResponseDto);
        }

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
