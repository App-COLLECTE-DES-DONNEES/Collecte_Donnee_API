package com.ditros.mcd.model.dto;

import com.ditros.mcd.model.entity.Accident;
import com.ditros.mcd.model.entity.Vehicle;
import com.ditros.mcd.model.entity.VehicleAction;
import com.ditros.mcd.model.entity.VehicleSpecialFunction;
import com.sun.mail.iap.ByteArray;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor @AllArgsConstructor
public class VehicleAccidentResp {
    private Long id;
    private Long vehicleAccidentNumber;
    private String plateNumber;

    private SpecialFunctionResp specialFunction;

    private VehicleResp vehicle;

    private VehicleActionResp action;

    private String insuranceName;

    private boolean isInsuranceValid;

    private byte[] image;
}
