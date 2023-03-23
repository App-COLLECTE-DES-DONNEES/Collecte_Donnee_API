package com.ditros.mcd.service;


import com.ditros.mcd.dao.AccidentTypeDao;
import com.ditros.mcd.model.dto.AccidentTypeResp;
import com.ditros.mcd.model.entity.AccidentType;
import com.ditros.mcd.model.mappers.AccidentTypeMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service @AllArgsConstructor
public class AccidentTypeService {

    private AccidentTypeMapper typeMapper;
    private AccidentTypeDao typeDao;

    public List<AccidentTypeResp> getAll(String lang) {
        if(lang==null) lang="fr";
        List<AccidentType> accidentTypes = typeDao.findByLang(lang);

        return accidentTypes.stream()
                .map(accidentType -> typeMapper.fromAccidentType(accidentType))
                .collect(Collectors.toList());
    }
}
