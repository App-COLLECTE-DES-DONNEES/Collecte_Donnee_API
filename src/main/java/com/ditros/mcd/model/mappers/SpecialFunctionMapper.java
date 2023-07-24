package com.ditros.mcd.model.mappers;

import com.ditros.mcd.model.dto.SpecialFunctionResp;
import com.ditros.mcd.model.dto.TrafficControlResp;
import com.ditros.mcd.model.entity.RoadTrafficControl;
import com.ditros.mcd.model.entity.VehicleSpecialFunction;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class SpecialFunctionMapper {

    public SpecialFunctionResp fromSpecialFunction(VehicleSpecialFunction specialFunction) {
        if(specialFunction==null) return null;
        SpecialFunctionResp functionResp = new SpecialFunctionResp();
        BeanUtils.copyProperties(specialFunction, functionResp);

        return functionResp;
    }
}
