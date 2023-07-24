package com.ditros.mcd.model.mappers;

import com.ditros.mcd.model.dto.RoadStateResp;
import com.ditros.mcd.model.entity.RoadState;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class RoadStateMapper {

    public RoadStateResp fromRoadState(RoadState roadState) {
        if(roadState==null) return null;
        RoadStateResp stateResp = new RoadStateResp();
        BeanUtils.copyProperties(roadState,stateResp);

        return stateResp;
    }
}
