package com.ditros.mcd.model.dto;

import com.ditros.mcd.model.entity.*;
import com.sun.mail.iap.ByteArray;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonAccidentResp {
    private Long id;
    private int personNumber;
    private int vehicleNumber;
    private int vehicleLinkedPedestrian;
    private String drivingLicenceYear;
    private int age;
    private PersonRoadTypeResp roadType;
    private AlcoholConsumptionResp alcoholConsumption;
    private AlcoholTestStatusResp testStatus;
    private AlcoholTestTypeResp testType;
    private AlcoholTestResultResp testResult;
    private PersonDrugUseResp drugUse;
    private SeatingRangeResp range;
    private SeatingPlaceResp place;
    private TraumaSeverityResp traumaSeverity;
    private ActionResp action;
    private WearingHelmetResp wearingHelmet;
    private OccupantRestraintSystemResp occupantRestraintSystem;
    private PersonResp person;
    private byte[] image;
}
