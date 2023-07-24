package com.ditros.mcd.model.mappers;

import com.ditros.mcd.model.dto.BrightnessConditionResp;
import com.ditros.mcd.model.entity.AccidentBrightnessCondition;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class BrightnessConditionMapper {

    public BrightnessConditionResp fromBrightnessCondition(AccidentBrightnessCondition condition) {
        BrightnessConditionResp conditionResp = new BrightnessConditionResp();
        BeanUtils.copyProperties(condition, conditionResp);

        return  conditionResp;
    }
}
