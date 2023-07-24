package com.ditros.mcd.service;


import com.ditros.mcd.dao.AccidentBrightnessConditionDao;
import com.ditros.mcd.model.dto.BrightnessConditionResp;
import com.ditros.mcd.model.entity.AccidentBrightnessCondition;
import com.ditros.mcd.model.mappers.BrightnessConditionMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service @AllArgsConstructor
public class BrightnessConditionService {

    private AccidentBrightnessConditionDao conditionDao;

    private BrightnessConditionMapper conditionMapper;

    public List<BrightnessConditionResp> getAll(String lang) {
        if(lang==null) lang="fr";
        List<AccidentBrightnessCondition> conditions = conditionDao.findByLang(lang);

        return conditions.stream()
                .map(condition -> conditionMapper.fromBrightnessCondition(condition))
                .collect(Collectors.toList());
    }
}
