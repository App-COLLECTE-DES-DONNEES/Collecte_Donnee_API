package com.ditros.mcd.model.mappers;

import com.ditros.mcd.model.dto.VehicleActionResp;
import com.ditros.mcd.model.dto.VehicleResp;
import com.ditros.mcd.model.entity.Vehicle;
import com.ditros.mcd.model.entity.VehicleAction;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VehicleMapper {
    private VehicleModelMapper modelMapper;
    private BrandMapper brandMapper;
    private VehicleTypeMapper typeMapper;

    public VehicleResp fromVehicle(Vehicle vehicle) {
        if(vehicle==null) return null;
        VehicleResp vehicleResp = new VehicleResp();
        vehicleResp.setId(vehicle.getId());
        vehicleResp.setChassis(vehicle.getChassis());
        vehicleResp.setCylinderCapacity(vehicle.getCylinderCapacity());
        vehicleResp.setFabricationYear(vehicle.getFabricationYear());
        vehicleResp.setType(typeMapper.fromVehicleModel(vehicle.getType()));
        vehicleResp.setBrand(brandMapper.fromBrand(vehicle.getBrand()));
        vehicleResp.setModel(modelMapper.fromVehicleModel(vehicle.getModel()));

        return vehicleResp;
    }
}
