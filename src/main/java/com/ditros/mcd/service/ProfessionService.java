package com.ditros.mcd.service;

import com.ditros.mcd.dao.ProfessionDao;
import com.ditros.mcd.model.dto.ProfessionResp;
import com.ditros.mcd.model.entity.Profession;
import com.ditros.mcd.model.mappers.ProfessionMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service @AllArgsConstructor
public class ProfessionService {
    private ProfessionDao professionDao;
    private ProfessionMapper professionMapper;

    public List<ProfessionResp> getAll(String lang) {
        if(lang==null) lang="fr";
        List<Profession> professions = professionDao.findByLang(lang);

        return professions.stream()
                .map(profession -> professionMapper.professionResp(profession))
                .collect(Collectors.toList());
    }
}
