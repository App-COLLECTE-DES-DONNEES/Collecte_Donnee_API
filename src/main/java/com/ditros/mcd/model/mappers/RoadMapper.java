package com.ditros.mcd.model.mappers;

import com.ditros.mcd.model.dto.RoadResp;
import com.ditros.mcd.model.entity.Road;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class RoadMapper {

    public RoadResp fromRoad(Road road) {
        if(road==null) return null;
        RoadResp roadResp = new RoadResp();
        BeanUtils.copyProperties(road, roadResp);

        return roadResp;
    }


}
