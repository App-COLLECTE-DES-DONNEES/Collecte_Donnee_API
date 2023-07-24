package com.ditros.mcd.model.mappers;

import com.ditros.mcd.model.dto.ProfessionResp;
import com.ditros.mcd.model.entity.Profession;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ProfessionMapper {
    public ProfessionResp professionResp(Profession profession) {
        if(profession==null) return null;
        ProfessionResp professionResp = new ProfessionResp();
        BeanUtils.copyProperties(profession, professionResp);

        return professionResp;
    }
}
