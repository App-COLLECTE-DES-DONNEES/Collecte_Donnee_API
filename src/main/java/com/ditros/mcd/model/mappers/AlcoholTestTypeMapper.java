package com.ditros.mcd.model.mappers;

import com.ditros.mcd.model.dto.AlcoholTestStatusResp;
import com.ditros.mcd.model.dto.AlcoholTestTypeResp;
import com.ditros.mcd.model.entity.AlcoholTestType;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class AlcoholTestTypeMapper {
    public AlcoholTestTypeResp fromAlcoholTestType(AlcoholTestType alcoholTestType) {
        if(alcoholTestType==null) return null;
        AlcoholTestTypeResp alcoholTestTypeResp = new AlcoholTestTypeResp();
        BeanUtils.copyProperties(alcoholTestType, alcoholTestTypeResp);

        return alcoholTestTypeResp;
    }
}
