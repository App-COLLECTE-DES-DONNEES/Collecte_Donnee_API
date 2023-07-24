package com.ditros.mcd.model.mappers;

import com.ditros.mcd.model.dto.RoadTypeResp;
import com.ditros.mcd.model.entity.RoadType;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class RoadTypeMapper {

    public RoadTypeResp fromRoadType(RoadType roadType) {
        if(roadType==null) return null;
        RoadTypeResp typeResp = new RoadTypeResp();
        BeanUtils.copyProperties(roadType, typeResp);

        return typeResp;
    }
}
