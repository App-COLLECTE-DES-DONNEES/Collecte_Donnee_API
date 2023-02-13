package com.ditros.mcd.model.mappers;

import com.ditros.mcd.model.dto.AlcoholConsumptionResp;
import com.ditros.mcd.model.dto.AlcoholTestStatusResp;
import com.ditros.mcd.model.entity.AlcoholTestStatus;
import com.ditros.mcd.model.entity.PersonAlcoholConsumption;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class AlcoholConsumptionMapper {
    public AlcoholConsumptionResp fromAlcoholConsumption(PersonAlcoholConsumption alcoholConsumption) {
        if(alcoholConsumption==null) return null;
        AlcoholConsumptionResp alcoholConsumptionResp = new AlcoholConsumptionResp();
        BeanUtils.copyProperties(alcoholConsumption, alcoholConsumptionResp);

        return alcoholConsumptionResp;
    }
}
