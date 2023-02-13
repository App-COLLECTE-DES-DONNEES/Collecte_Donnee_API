package com.ditros.mcd.model.mappers;

import com.ditros.mcd.model.dto.TraumaSeverityResp;
import com.ditros.mcd.model.entity.PersonTraumaSeverity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class TraumaSeverityMapper {

    public TraumaSeverityResp fromTraumaSeverity(PersonTraumaSeverity traumaSeverity) {
        if(traumaSeverity==null) return null;
        TraumaSeverityResp severityResp = new TraumaSeverityResp();
        BeanUtils.copyProperties(traumaSeverity, severityResp);

        return severityResp;
    }
}
