package com.ditros.mcd.model.mappers;

import com.ditros.mcd.model.dto.ClimaticConditionResp;
import com.ditros.mcd.model.entity.AccidentClimaticCondition;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ClimaticConditionMapper {

    public ClimaticConditionResp fromClimaticCondition(AccidentClimaticCondition climaticCondition) {
        if(climaticCondition==null) return null;
        ClimaticConditionResp conditionResp = new ClimaticConditionResp();
        BeanUtils.copyProperties(climaticCondition, conditionResp);

        return conditionResp;
    }
}
