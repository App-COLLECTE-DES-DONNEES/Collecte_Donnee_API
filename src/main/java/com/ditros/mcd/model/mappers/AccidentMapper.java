package com.ditros.mcd.model.mappers;

import com.ditros.mcd.model.dto.*;
import com.ditros.mcd.model.entity.Accident;
import com.ditros.mcd.util.DateUtil;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class AccidentMapper {
    public AccidentReq fromAccident(Accident accident){
        if(accident == null)
            return null;
        return new AccidentReq(
                accident.getId(),
                accident.getCrashDate(),
                accident.getCrashTime(),
                accident.getMunicipality().getId(),
                accident.getLatitude(),
                accident.getLongitude(),
                accident.getPlace(),
                accident.getRoad() == null? null : accident.getRoad() .getId(),
                accident.getRoadType().getId(),
                accident.getRoadCategory().getId(),
                accident.getSpeedLimit(),
                accident.getBlock().getId(),
                accident.getRoadState().getId(),
                accident.getRoadIntersection().getId(),
                accident.getTrafficControl().getId(),
                accident.getVirage().getId(),
                accident.getRoadSlopSection().getId(),
                accident.getAccidentType().getId(),
                accident.getImpactType().getId(),
                accident.getClimaticCondition().getId(),
                accident.getBrightnessCondition().getId(),
                accident.getAccidentSeverity().getId(),
                accident.getVehicleAccidents().stream()
                        .map(vehicleAccident ->
                                new VehiculeReq(
                                        vehicleAccident.getId(),
                                        vehicleAccident.getVehicleAccidentNumber(),
                                        vehicleAccident.getPlateNumber(),
                                        vehicleAccident.getVehicle() ==null? null : vehicleAccident.getVehicle().getChassis(),
                                        vehicleAccident.getVehicle() ==null? null : vehicleAccident.getVehicle().getId(),
                                        vehicleAccident.getSpecialFunction().getId(),
                                        vehicleAccident.getVehicle() ==null? null : vehicleAccident.getVehicle().getBrand().getId(),
                                        vehicleAccident.getVehicle() ==null? null : vehicleAccident.getVehicle().getModel().getId(),
                                        vehicleAccident.getVehicle() ==null? null : vehicleAccident.getVehicle().getFabricationYear().intValue(),
                                        vehicleAccident.getVehicle() ==null? null : vehicleAccident.getVehicle().getCylinderCapacity().intValue(),
                                        vehicleAccident.getSpecialFunction().getId(),
                                        vehicleAccident.getAction().getId(),
                                        null,
                                        null
                                ))
                        .collect(Collectors.toList()),
                accident.getPersonAccidentList().stream()
                        .map(personAccident -> new PersonDtoReq(
                                personAccident.getId(),
                                personAccident.getPerson().getFirstName(),
                                personAccident.getPerson().getLastName(),
                                personAccident.getPerson().getIdentityNumber(),
                                personAccident.getPerson().getPhone(),
                                personAccident.getPersonNumber(),
                                personAccident.getVehicleNumber(),
                                personAccident.getVehicleLinkedPedestrian(),
                                DateUtil.textFromDate("dd/MM/yyyy", personAccident.getPerson().getBirthDate()),
                                personAccident.getPerson().getGender()==null? null : personAccident.getPerson().getGender().getId(),
                                personAccident.getRoadType().getId(),
                                personAccident.getRange().getId(),
                                personAccident.getPlace().getId(),
                                personAccident.getTraumaSeverity().getId(),
                                personAccident.getWearingHelmet().getId(),
                                personAccident.getOccupantRestraintSystem().getId(),
                                personAccident.getAction().getId(),
                                personAccident.getAlcoholConsumption().getId(),
                                personAccident.getTestStatus().getId(),
                                personAccident.getTestValue(),
                                personAccident.getTestType().getId(),
                                personAccident.getTestResult().getId(),
                                personAccident.getDrugUse().getId(),
                                personAccident.getDrivingLicenceYear(),
                                personAccident.getCare()==null? 0 : personAccident.getCare().getId(),
                                personAccident.getPerson().getId(),
                                null,
                                null,
                                null,
                                null,
                                null,
                                null,
                                null,
                                0.0,
                                0.0,
                                null,
                                personAccident.getProfession().getId(),
                                personAccident.getDrivingLicence(),
                                personAccident.getDrivingLicenceType()
                        ))
                        .collect(Collectors.toList()),
                accident.getCity().getId(),
                accident.getStatus(),
                accident.getDirectCauses().stream().map(directCause -> new DirectCauseResp(directCause.getId(), directCause.getName())).collect(Collectors.toList()),
                null,
                null
        );
    }
}
