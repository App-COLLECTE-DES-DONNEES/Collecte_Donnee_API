package com.ditros.mcd.model.dto;

import lombok.*;

import java.util.List;
import java.util.Map;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VehicleReqResp {
    private Long id;
    private Long vehicleAccidentNumber;
    private String plate;
    private String chassis;
    private Long vehicleId;
    private Long type;
    private Long brand;
    private Long model;
    private int fabricationYear;
    private int cylinderCapacity;
    private Long specialFunction;
    private Long vehicleAction;
    private List<Map<String, String>> vehicleImages;
}
