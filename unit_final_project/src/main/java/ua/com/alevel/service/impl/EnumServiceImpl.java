package ua.com.alevel.service.impl;


import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.entity.doctor.Category;
import ua.com.alevel.persistence.entity.doctor.Specialization;
import ua.com.alevel.persistence.entity.doctor.Types;
import ua.com.alevel.persistence.repository.doctor.CategoryRepository;
import ua.com.alevel.persistence.repository.doctor.SpecializationRepository;
import ua.com.alevel.service.EnumService;
import ua.com.alevel.util.EnumsNamesConverterUtil;

import java.util.List;

@Service
public class EnumServiceImpl<ENITY extends BaseEntity & Types> implements EnumService<ENITY> {

    final CategoryRepository categoryRepository;
    final SpecializationRepository specializationRepository;

    public EnumServiceImpl(CategoryRepository categoryRepository, SpecializationRepository specializationRepository) {
        this.categoryRepository = categoryRepository;
        this.specializationRepository = specializationRepository;
    }

    @Override
    public List<ENITY> findAll(Class<ENITY> enityClass) {
        List<ENITY> enities = null;
        if (enityClass == Category.class) {
            enities = (List<ENITY>) categoryRepository.findAll();
        } else if (enityClass == Specialization.class) {
            enities = (List<ENITY>) specializationRepository.findAll();
        } else {
            throw new NotImplementedException();
        }
        for (ENITY en :
                enities) {
            en.setName(EnumsNamesConverterUtil.getString(en));
        }
        return enities;
    }
}
