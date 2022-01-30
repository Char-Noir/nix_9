package ua.com.alevel.facade;

import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.entity.doctor.Types;

import java.util.List;

public interface EnumFacade<ENNITY extends BaseEntity & Types>  {


    List<ENNITY> findAll(Class<ENNITY> enityClass);
}
