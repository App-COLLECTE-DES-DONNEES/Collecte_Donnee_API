package com.ditros.mcd.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDtoReq {
    private Long id;
    private String firstName;
    private String lastName;
    private String cni;
    private String telephone;
    private int personAccidentNumber;
    private int vehicleAccidentNumber;
    private int vehicleLinkedPedestrian;
    private String birthDate;
    private Long gender;
    private Long roadType;
    private Long range;
    private Long place;
    private Long traumaSeverity;
    private Long wearingHelmet;
    private Long occupantRestraintSystem;
    private Long personAction;
    private Long alcoholConsumption;
    private Long testStatus;
    private double testValue;
    private Long testType;
    private Long testResult;
    private Long drugUse;
    private String drivingLicenceYear;
    private Long care;
    private Long personId;
    private List<ImageVueFormat> images;
    private String address;
    private String street;
    private String postal;
    private String lang;
    private String tel1;
    private String tel2;
    private double latitude;
    private double longitude;
    private List<DocumentReq> personDocuments;
    private Long profession;
    private String drivingLicence;
    private String drivingLicenceType;
}
