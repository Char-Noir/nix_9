package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.entity.doctor.Types;

import java.util.List;

public interface EnumService<ENITY extends BaseEntity & Types> {

    List<ENITY> findAll(Class<ENITY> enityClass);
}
