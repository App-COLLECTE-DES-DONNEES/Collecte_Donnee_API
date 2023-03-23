package com.ditros.mcd.service;

import com.ditros.mcd.dao.AccidentClimaticConditionDao;
import com.ditros.mcd.model.dto.ClimaticConditionResp;
import com.ditros.mcd.model.entity.AccidentClimaticCondition;
import com.ditros.mcd.model.mappers.ClimaticConditionMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClimaticConditionService {

    private ClimaticConditionMapper conditionMapper;

    private AccidentClimaticConditionDao conditionDao;

    public List<ClimaticConditionResp> getAll(String lang) {
        if(lang==null) lang="fr";
        List<AccidentClimaticCondition> climaticConditions = conditionDao.findByLang(lang);

        return climaticConditions.stream()
                .map(climaticCondition -> conditionMapper.fromClimaticCondition(climaticCondition))
                .collect(Collectors.toList());
    }
}
