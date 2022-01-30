package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.facade.DoctorFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.doctor.Category;
import ua.com.alevel.persistence.entity.doctor.Doctor;
import ua.com.alevel.persistence.entity.doctor.Specialization;
import ua.com.alevel.service.DoctorService;
import ua.com.alevel.util.WebUtil;
import ua.com.alevel.web.dto.request.doctor.DoctorRequestDto;
import ua.com.alevel.web.dto.response.PageData;
import ua.com.alevel.web.dto.response.doctor.DoctorResponseDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoctorFacadeImpl implements DoctorFacade {

    private final DoctorService doctorService;

    public DoctorFacadeImpl(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @Override
    public void create(DoctorRequestDto doctorRequestDto) {
        doctorService.create(doctorRequestDto.getDoctor());
    }

    @Override
    public void update(DoctorRequestDto doctorRequestDto, Long id) {
        Optional<Doctor> doctor = doctorService.findById(id);
        if (doctor.isPresent()) {
            Doctor doctor1 = doctor.get();
            doctor1.setDoctorNote(doctorRequestDto.getNote());
            doctor1.setName(doctorRequestDto.getName());
            doctor1.setDateOfCertification(doctorRequestDto.getDateOfCertification());
            doctor1.setAssessmentOfCertification(doctorRequestDto.getAssessmentOfSertification());
            doctor1.setSpecialization(new Specialization(doctorRequestDto.getSpecialization()));
            doctor1.setCategory(new Category(doctorRequestDto.getCategory()));
            doctorService.update(doctor1);
        }
        throw new EntityNotFoundException("There are no such doctor");
    }

    @Override
    public void delete(Long id) {
        doctorService.delete(id);
    }

    @Override
    public DoctorResponseDto findById(Long id) {
        Optional<Doctor> doctor = doctorService.findById(id);
        if (doctor.isPresent()) {
            return new DoctorResponseDto(doctor.get());
        }
        throw new EntityNotFoundException("There are no such doctor");
    }

    @Override
    public PageData<DoctorResponseDto> findAll(WebRequest request) {
        DataTableRequest dataTableRequest = WebUtil.generateDataTableRequestByWebRequest(request);
        DataTableResponse<Doctor> tableResponse = doctorService.findAll(dataTableRequest);
        List<DoctorResponseDto> items = tableResponse.getItems().stream().
                map(DoctorResponseDto::new).
                collect(Collectors.toList());

        PageData<DoctorResponseDto> pageData = (PageData<DoctorResponseDto>) WebUtil.initPageData(tableResponse);
        pageData.setItems(items);
        return pageData;
    }

    @Override
    public long count() {
        return doctorService.count();
    }

    @Override
    public DoctorResponseDto findByUserId(Long id) {
        Optional<Doctor> doctor = doctorService.findByUserId(id);
        if (doctor.isPresent()) {
            return new DoctorResponseDto(doctor.get());
        }
        throw new EntityNotFoundException("There are no such doctor");
    }

    @Override
    public PageData<DoctorResponseDto> findNotDeleted(WebRequest request) {
        DataTableRequest dataTableRequest = WebUtil.generateDataTableRequestByWebRequest(request);
        DataTableResponse<Doctor> tableResponse = doctorService.findNotDeleted(dataTableRequest);
        List<DoctorResponseDto> items = tableResponse.getItems().stream().
                map(DoctorResponseDto::new).
                collect(Collectors.toList());

        PageData<DoctorResponseDto> pageData = (PageData<DoctorResponseDto>) WebUtil.initPageData(tableResponse);
        pageData.setItems(items);
        return pageData;
    }
}
