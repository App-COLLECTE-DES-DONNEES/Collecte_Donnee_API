package com.ditros.mcd.model.mappers;

import com.ditros.mcd.model.dto.VehicleActionResp;
import com.ditros.mcd.model.entity.VehicleAction;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class VehicleActionMapper {
    public VehicleActionResp fromVehicleAction(VehicleAction action) {
        if(action==null) return null;
        VehicleActionResp vehicleActionResp = new VehicleActionResp();
        BeanUtils.copyProperties(action, vehicleActionResp);

        return vehicleActionResp;
    }
}
