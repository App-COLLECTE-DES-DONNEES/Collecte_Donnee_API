package com.ditros.mcd.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor @NoArgsConstructor
public class VehicleResp {
    private Long id;
    private String chassis;
    private Long cylinderCapacity;
    private Long fabricationYear;
    private VehicleTypeResp type;
    private BrandResp brand;
    private VehicleModelResp model;

}
