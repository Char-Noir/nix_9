package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.facade.EnumFacade;
import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.entity.doctor.Types;
import ua.com.alevel.service.EnumService;

import java.util.List;

@Service
public class EnumFacadeImpl<ENITY extends BaseEntity & Types> implements EnumFacade<ENITY> {

    final EnumService<ENITY> enityEnumService;

    public EnumFacadeImpl(EnumService<ENITY> enityEnumService) {
        this.enityEnumService = enityEnumService;
    }

    @Override
    public List<ENITY> findAll(Class<ENITY> enityClass) {
        return enityEnumService.findAll(enityClass);
    }
}
