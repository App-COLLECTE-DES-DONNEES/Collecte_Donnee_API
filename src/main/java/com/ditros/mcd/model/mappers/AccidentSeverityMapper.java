package com.ditros.mcd.model.mappers;

import com.ditros.mcd.model.dto.AccidentSeverityResp;
import com.ditros.mcd.model.entity.AccidentSeverity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class AccidentSeverityMapper {

    public AccidentSeverityResp fromSeverity(AccidentSeverity severity) {
        if(severity==null) return null;
        AccidentSeverityResp severityResp = new AccidentSeverityResp();
        BeanUtils.copyProperties(severity, severityResp);

        return severityResp;
    }
}
