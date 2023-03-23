package com.ditros.mcd.service;

import com.ditros.mcd.dao.PersonDrugUseDao;
import com.ditros.mcd.dao.PersonRoadTypeDao;
import com.ditros.mcd.model.dto.PersonDrugUseResp;
import com.ditros.mcd.model.dto.PersonRoadTypeResp;
import com.ditros.mcd.model.entity.PersonDrugUse;
import com.ditros.mcd.model.entity.PersonRoadType;
import com.ditros.mcd.model.mappers.PersonDrugUseMapper;
import com.ditros.mcd.model.mappers.PersonRoadTypeMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PersonDrugUseService {
    private PersonDrugUseMapper drugUseMapper;

    private PersonDrugUseDao drugUseDao;

    public List<PersonDrugUseResp> getAll(String lang) {
        if(lang==null) lang="fr";
        List<PersonDrugUse> drugUses = drugUseDao.findByLang(lang);

        return drugUses.stream()
                .map(roadType -> drugUseMapper.fromPersonDrugUse(roadType))
                .collect(Collectors.toList());
    }
}
