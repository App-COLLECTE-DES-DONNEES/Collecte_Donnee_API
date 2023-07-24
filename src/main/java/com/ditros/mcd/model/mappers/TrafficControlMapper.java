package com.ditros.mcd.model.mappers;

import com.ditros.mcd.model.dto.TrafficControlResp;
import com.ditros.mcd.model.entity.RoadTrafficControl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class TrafficControlMapper {

    public TrafficControlResp fromTrafficControlResp(RoadTrafficControl trafficControl) {
        if(trafficControl==null) return null;
        TrafficControlResp controlResp = new TrafficControlResp();
        BeanUtils.copyProperties(trafficControl, controlResp);

        return controlResp;
    }
}
