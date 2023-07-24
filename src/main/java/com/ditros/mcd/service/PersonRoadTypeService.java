package com.ditros.mcd.service;


import com.ditros.mcd.dao.PersonRoadTypeDao;
import com.ditros.mcd.model.dto.PersonRoadTypeResp;
import com.ditros.mcd.model.entity.PersonRoadType;
import com.ditros.mcd.model.mappers.PersonRoadTypeMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service @AllArgsConstructor
public class PersonRoadTypeService {

    private PersonRoadTypeMapper roadTypeMapper;

    private PersonRoadTypeDao roadTypeDao;

    public List<PersonRoadTypeResp> getAll(String lang) {
        if(lang==null) lang="fr";
        List<PersonRoadType> roadTypes = roadTypeDao.findByLang(lang);

        return roadTypes.stream()
                .map(roadType -> roadTypeMapper.fromPersonRoadType(roadType))
                .collect(Collectors.toList());
    }

}
