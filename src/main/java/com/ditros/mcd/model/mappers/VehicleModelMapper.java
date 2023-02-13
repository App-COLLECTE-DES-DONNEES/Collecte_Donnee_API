package com.ditros.mcd.model.mappers;

import com.ditros.mcd.model.dto.VehicleModelResp;
import com.ditros.mcd.model.entity.VehicleModel;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class VehicleModelMapper {

    public VehicleModelResp fromVehicleModel(VehicleModel vehicleModel) {
        VehicleModelResp vehicleModelResp = new VehicleModelResp();
        BeanUtils.copyProperties(vehicleModel, vehicleModelResp);

        return vehicleModelResp;
    }
}
