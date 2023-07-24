package com.ditros.mcd.model.mappers;

import com.ditros.mcd.model.dto.SeatingPlaceResp;
import com.ditros.mcd.model.dto.SeatingRangeResp;
import com.ditros.mcd.model.entity.SeatingPlace;
import com.ditros.mcd.model.entity.SeatingRange;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class SeatingPlaceMapper {
    public SeatingPlaceResp fromPlace(SeatingPlace seatingPlace) {

        if(seatingPlace==null) return null;
        SeatingPlaceResp seatingPlaceResp = new SeatingPlaceResp();
        BeanUtils.copyProperties(seatingPlace, seatingPlaceResp);
        return seatingPlaceResp;
    }

}
