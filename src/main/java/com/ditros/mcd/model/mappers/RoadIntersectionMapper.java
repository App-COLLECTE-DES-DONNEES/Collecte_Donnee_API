package com.ditros.mcd.model.mappers;

import com.ditros.mcd.model.dto.RoadIntersectionResp;
import com.ditros.mcd.model.entity.RoadIntersection;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class RoadIntersectionMapper {

    public RoadIntersectionResp fromRoadIntersection(RoadIntersection intersection) {
        if(intersection==null) return null;
        RoadIntersectionResp intersectionResp = new RoadIntersectionResp();
        BeanUtils.copyProperties(intersection, intersectionResp);

        return intersectionResp;
    }
}
