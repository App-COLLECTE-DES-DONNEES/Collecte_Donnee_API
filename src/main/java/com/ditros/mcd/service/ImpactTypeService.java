package com.ditros.mcd.service;

import com.ditros.mcd.dao.AccidentImpactTypeDao;
import com.ditros.mcd.model.dto.ImpactTypeResp;
import com.ditros.mcd.model.entity.AccidentImpactType;
import com.ditros.mcd.model.mappers.ImpactTypeMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ImpactTypeService {

    private ImpactTypeMapper typeMapper;

    private AccidentImpactTypeDao impactTypeDao;

    public List<ImpactTypeResp> getAll(String lang) {
        if(lang==null) lang="fr";
        List<AccidentImpactType> impactTypes = impactTypeDao.findByLang(lang);

        return impactTypes.stream()
                .map(impactType -> typeMapper.fromImpactType(impactType))
                .collect(Collectors.toList());
    }
}
