package com.ditros.mcd.service;

import com.ditros.mcd.dao.InjuryDao;
import com.ditros.mcd.model.dto.AccidentListResp;
import com.ditros.mcd.model.dto.InjuryResp;
import com.ditros.mcd.model.entity.Injury;
import com.ditros.mcd.model.mappers.InjuryMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class InjuryService {
    private InjuryDao injuryDao;
    private InjuryMapper injuryMapper;

    public List<InjuryResp> getInjuriesByName(String name, String lang){
        if(lang==null) lang="fr";
        List<InjuryResp> models = new ArrayList<>();
        if(name.equals(""))
            return models;
        List<Injury> injuries = injuryDao.getInjuryByName(name, lang);

        injuries.forEach(
                injury -> models.add(injuryMapper.fromInjury(injury))
        );
        return models;
    }

    public Page<InjuryResp> getInjuries(int size, int page, String lang){
        if(lang==null) lang="fr";
        List<InjuryResp> models = new ArrayList<>();
        Pageable pageable = PageRequest.of(page, size);

        Page<Injury> injuries = injuryDao.findByActiveStatusTrueAndLangOrderByNameDesc(lang, pageable);

        injuries.forEach(
                injury -> models.add(injuryMapper.fromInjury(injury))
        );
        return new PageImpl<InjuryResp>(models, pageable, injuries.getTotalElements());
    }

}
