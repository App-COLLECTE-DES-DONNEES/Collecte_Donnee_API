package com.ditros.mcd.model.mappers;

import com.ditros.mcd.model.dto.VehicleTypeResp;
import com.ditros.mcd.model.entity.VehicleType;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class VehicleTypeMapper {

    public VehicleTypeResp fromVehicleModel(VehicleType vehicleType) {
        VehicleTypeResp vehicleTypeResp = new VehicleTypeResp();
        BeanUtils.copyProperties(vehicleType, vehicleTypeResp);

        return vehicleTypeResp;
    }
}
