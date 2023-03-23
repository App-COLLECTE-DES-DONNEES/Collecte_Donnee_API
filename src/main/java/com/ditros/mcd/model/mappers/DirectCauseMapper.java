package com.ditros.mcd.model.mappers;

import com.ditros.mcd.model.dto.AccidentResp;
import com.ditros.mcd.model.dto.ClimaticConditionResp;
import com.ditros.mcd.model.dto.DirectCauseResp;
import com.ditros.mcd.model.entity.AccidentClimaticCondition;
import com.ditros.mcd.model.entity.DirectCause;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class DirectCauseMapper {

    public DirectCauseResp fromDirectCause(DirectCause directCause) {
        if(directCause==null) return null;
        DirectCauseResp directCauseResp = new DirectCauseResp();
        BeanUtils.copyProperties(directCause, directCauseResp);

        return directCauseResp;
    }
}
