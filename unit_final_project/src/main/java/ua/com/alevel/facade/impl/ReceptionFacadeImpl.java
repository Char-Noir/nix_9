package ua.com.alevel.facade.impl;


import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.facade.ReceptionFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.reception.Reception;
import ua.com.alevel.service.ReceptionService;
import ua.com.alevel.util.WebUtil;
import ua.com.alevel.web.dto.request.reception.ReceptionRequestDto;
import ua.com.alevel.web.dto.response.PageData;
import ua.com.alevel.web.dto.response.reception.ReceptionResponseDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReceptionFacadeImpl implements ReceptionFacade {

    private final ReceptionService receptionService;

    public ReceptionFacadeImpl(ReceptionService receptionService) {
        this.receptionService = receptionService;
    }

    @Override
    public void create(ReceptionRequestDto receptionRequestDto) {
        receptionService.create(receptionRequestDto.getReception());
    }

    @Override
    public void update(ReceptionRequestDto receptionRequestDto, Long id) {
        Optional<Reception> doctor = receptionService.findById(id);
        if (doctor.isPresent()) {
            Reception doctor1 = doctor.get();
            doctor1.setReceptionPrice(receptionRequestDto.getReceptionPrice());
            doctor1.setReviewComment(receptionRequestDto.getReviewComment());
            doctor1.setReceptionTime(receptionRequestDto.getDateTime().toLocalTime());
            doctor1.setDateOfReception(receptionRequestDto.getDateTime().toLocalDate());
            receptionService.update(doctor1);
            return;
        }
        throw new EntityNotFoundException("There are no such receptions");
    }

    @Override
    public void delete(Long id) {
        receptionService.delete(id);
    }

    @Override
    public ReceptionResponseDto findById(Long id) {
        Optional<Reception> doctor = receptionService.findById(id);
        if (doctor.isPresent()) {
            return new ReceptionResponseDto(doctor.get());
        }
        throw new EntityNotFoundException("There are no such receptions");
    }

    @Override
    public PageData<ReceptionResponseDto> findAll(WebRequest request) {
        DataTableRequest dataTableRequest = WebUtil.generateDataTableRequestByWebRequest(request);
        DataTableResponse<Reception> tableResponse = receptionService.findAll(dataTableRequest);
        List<ReceptionResponseDto> items = tableResponse.getItems().stream().
                map(ReceptionResponseDto::new).
                collect(Collectors.toList());

        PageData<ReceptionResponseDto> pageData = (PageData<ReceptionResponseDto>) WebUtil.initPageData(tableResponse);
        pageData.setItems(items);
        return pageData;
    }

    @Override
    public Long count() {
        return receptionService.count();
    }

    @Override
    public PageData<ReceptionResponseDto> findAllByPatient(WebRequest request, Long id) {
        DataTableRequest dataTableRequest = WebUtil.generateDataTableRequestByWebRequest(request);
        DataTableResponse<Reception> tableResponse = receptionService.findAllByPatient(id, dataTableRequest);
        List<ReceptionResponseDto> items = tableResponse.getItems().stream().
                map(ReceptionResponseDto::new).
                collect(Collectors.toList());

        PageData<ReceptionResponseDto> pageData = (PageData<ReceptionResponseDto>) WebUtil.initPageData(tableResponse);
        pageData.setItems(items);
        return pageData;
    }

    @Override
    public PageData<ReceptionResponseDto> findAllByDoctor(WebRequest request, Long id) {
        DataTableRequest dataTableRequest = WebUtil.generateDataTableRequestByWebRequest(request);
        DataTableResponse<Reception> tableResponse = receptionService.findAllByDoctor(id, dataTableRequest);
        List<ReceptionResponseDto> items = tableResponse.getItems().stream().
                map(ReceptionResponseDto::new).
                collect(Collectors.toList());

        PageData<ReceptionResponseDto> pageData = (PageData<ReceptionResponseDto>) WebUtil.initPageData(tableResponse);
        pageData.setItems(items);
        return pageData;
    }
}
