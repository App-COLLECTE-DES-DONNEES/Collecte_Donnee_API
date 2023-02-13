package com.ditros.mcd.model.mappers;

import com.ditros.mcd.model.dto.RoadSlopSectionResp;
import com.ditros.mcd.model.entity.RoadSlopSection;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class RoadSlopSectionMapper {

    public RoadSlopSectionResp fromRoadSlopSection(RoadSlopSection slopSection) {

        if(slopSection==null) return null;
        RoadSlopSectionResp slopSectionResp = new RoadSlopSectionResp();
        BeanUtils.copyProperties(slopSection, slopSectionResp);

        return slopSectionResp;
    }
}
