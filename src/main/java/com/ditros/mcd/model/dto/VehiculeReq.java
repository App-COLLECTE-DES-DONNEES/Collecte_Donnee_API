package com.ditros.mcd.model.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VehiculeReq {
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
    private List<ImageVueFormat> vehicleImages;
    private List<DocumentReq> vehicleDocuments;
}
