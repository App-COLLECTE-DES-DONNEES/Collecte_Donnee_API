package com.ditros.mcd.model.mappers;

import com.ditros.mcd.model.dto.TreatmentResp;
import com.ditros.mcd.model.dto.VehicleAccidentResp;
import com.ditros.mcd.model.entity.Treatment;
import com.ditros.mcd.model.entity.VehicleAccident;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VehicleAccidentMapper {
    private SpecialFunctionMapper specialFunctionMapper;
    private VehicleMapper vehicleMapper;
    private VehicleActionMapper actionMapper;

    public VehicleAccidentResp fromVehiculeAccident(VehicleAccident vehicleAccident) {
        VehicleAccidentResp vehicleAccidentResp = new VehicleAccidentResp();
        vehicleAccidentResp.setVehicleAccidentNumber(vehicleAccident.getVehicleAccidentNumber());
        vehicleAccidentResp.setPlateNumber(vehicleAccident.getPlateNumber());
        vehicleAccidentResp.setSpecialFunction(specialFunctionMapper.fromSpecialFunction(vehicleAccident.getSpecialFunction()));
        vehicleAccidentResp.setVehicle(vehicleMapper.fromVehicle(vehicleAccident.getVehicle()));
        vehicleAccidentResp.setAction(actionMapper.fromVehicleAction(vehicleAccident.getAction()));
        vehicleAccidentResp.setInsuranceName(vehicleAccident.getInsuranceName());
        vehicleAccidentResp.setInsuranceValid(vehicleAccident.isInsuranceValid());

        return vehicleAccidentResp;
    }
}
