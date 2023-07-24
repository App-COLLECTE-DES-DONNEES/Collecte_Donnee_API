package com.ditros.mcd.model.mappers;

import com.ditros.mcd.model.dto.AlcoholTestStatusResp;
import com.ditros.mcd.model.dto.AlcoholTestTypeResp;
import com.ditros.mcd.model.entity.AlcoholTestStatus;
import com.ditros.mcd.model.entity.AlcoholTestType;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class AlcoholTestStatusMapper {
    public AlcoholTestStatusResp fromAlcoholTestStatus(AlcoholTestStatus alcoholTestStatus) {
        if(alcoholTestStatus==null) return null;
        AlcoholTestStatusResp alcoholTestTypeResp = new AlcoholTestStatusResp();
        BeanUtils.copyProperties(alcoholTestStatus, alcoholTestTypeResp);

        return alcoholTestTypeResp;
    }
}
