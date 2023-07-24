package com.ditros.mcd.model.dto;

import com.ditros.mcd.model.entity.PersonAccident;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor
public class AccidentRespOne {
    private Long id;
    private String accidentDate; //JJMMAAAA
    private String accidentTime;
    private String municipality;
    private double latitude;
    private double longitude;
    private String place;
    private RoadResp road;
    private RoadTypeResp roadType;
    private RoadCategoryResp roadCategory;
    private int speedLimit;
    private RoadBlockResp block;
    private RoadStateResp roadState;
    private RoadIntersectionResp roadIntersection;
    private TrafficControlResp roadTrafficControl;
    private VirageResp virage;
    private RoadSlopSectionResp roadSlopSection;
    private AccidentTypeResp accidentType;
    private ImpactTypeResp impactType;
    private ClimaticConditionResp climaticCondition;
    private BrightnessConditionResp brightnessCondition;
    private AccidentSeverityResp accidentSeverity;
    private List<VehicleAccidentResp> vehicules;
    private List<PersonAccidentResp> persons;
    private CityResp city;
}
