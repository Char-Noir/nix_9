package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.facade.PatientFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.patient.Patient;
import ua.com.alevel.service.PatientService;
import ua.com.alevel.util.WebUtil;
import ua.com.alevel.web.dto.request.patient.PatientRequestDto;
import ua.com.alevel.web.dto.response.PageData;
import ua.com.alevel.web.dto.response.patient.PatientResponseDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientFacadeImpl implements PatientFacade {

    private final PatientService patientService;

    public PatientFacadeImpl(PatientService patientService) {
        this.patientService = patientService;
    }

    @Override
    public void create(PatientRequestDto patientRequestDto) {
        patientService.create(patientRequestDto.getPatient());
    }

    @Override
    public void update(PatientRequestDto patientRequestDto, Long id) {
        Optional<Patient> patient = patientService.findById(id);
        if (patient.isPresent()) {
            Patient patient1 = patient.get();
            patient1.setDateOfBirth(patientRequestDto.getDateOfBirth());
            patient1.setUserDocuments(patientRequestDto.getUserDocuments());
            patient1.setPhoneNumber(patientRequestDto.getPhoneNumber());
            patient1.setName(patientRequestDto.getName());
            patientService.update(patient1);
        }
        throw new EntityNotFoundException("There are no such patient");
    }

    @Override
    public void delete(Long id) {
        patientService.delete(id);
    }

    @Override
    public PatientResponseDto findById(Long id) {
        Optional<Patient> patient = patientService.findById(id);
        if (patient.isPresent()) {
            return new PatientResponseDto(patient.get());
        }
        throw new EntityNotFoundException("There are no such patient");
    }

    @Override
    public PageData<PatientResponseDto> findAll(WebRequest request) {
        DataTableRequest dataTableRequest = WebUtil.generateDataTableRequestByWebRequest(request);
        DataTableResponse<Patient> tableResponse = patientService.findAll(dataTableRequest);
        List<PatientResponseDto> items = tableResponse.getItems().stream().
                map(PatientResponseDto::new).
                collect(Collectors.toList());

        PageData<PatientResponseDto> pageData = (PageData<PatientResponseDto>) WebUtil.initPageData(tableResponse);
        pageData.setItems(items);
        return pageData;
    }

    @Override
    public long count() {
        return patientService.count();
    }

    @Override
    public PatientResponseDto findByUserId(Long id) {
        Optional<Patient> patient = patientService.findByUserId(id);
        if (patient.isPresent()) {
            return new PatientResponseDto(patient.get());
        }
        throw new EntityNotFoundException("There are no such patient");
    }
}
