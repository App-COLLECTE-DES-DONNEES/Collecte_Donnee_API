package com.ditros.mcd.model.mappers;

import com.ditros.mcd.model.dto.SeatingRangeResp;
import com.ditros.mcd.model.entity.SeatingRange;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class SeatingRangeMapper {

    public SeatingRangeResp fromRange(SeatingRange seatingRange) {

        if(seatingRange==null) return null;
        SeatingRangeResp seatingRangeResp = new SeatingRangeResp();
        BeanUtils.copyProperties(seatingRange, seatingRangeResp);
        return seatingRangeResp;
    }

}
