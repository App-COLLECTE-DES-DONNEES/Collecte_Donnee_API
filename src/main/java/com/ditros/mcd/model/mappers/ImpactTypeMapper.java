package com.ditros.mcd.model.mappers;

import com.ditros.mcd.model.dto.ImpactTypeResp;
import com.ditros.mcd.model.entity.AccidentImpactType;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ImpactTypeMapper {

    public ImpactTypeResp fromImpactType(AccidentImpactType impactType) {
        if(impactType==null) return null;
        ImpactTypeResp impactTypeResp = new ImpactTypeResp();
        BeanUtils.copyProperties(impactType, impactTypeResp);

        return impactTypeResp;
    }
}
