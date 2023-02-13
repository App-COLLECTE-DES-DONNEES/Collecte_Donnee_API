package com.ditros.mcd.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonReqResp {
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
    private Long testType;
    private Long testResult;
    private Long drugUse;
    private String drivingLicenceYear;
    private Long care;
    private Long personId;
    private List<Map<String, String>> images;
    private Long profession;
}
