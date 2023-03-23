package com.ditros.mcd.model.dto;

import com.ditros.mcd.enumeration.AccidentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccidentReq {
    private Long id;
    private String accidentDate; //JJMMAAAA
    private String accidentTime;
    private Long municipality;
    private double latitude;
    private double longitude;
    private String place;
    private Long road;
    private Long roadType;
    private Long roadCategory;
    private int speedLimit;
    private Long block;
    private Long roadState;
    private Long roadIntersection;
    private Long roadTrafficControl;
    private Long virage;
    private Long roadSlopSection;
    private Long accidentType;
    private Long impactType;
    private Long climaticCondition;
    private Long brightnessCondition;
    private Long accidentSeverity;
    private List<VehiculeReq> vehicules;
    private List<PersonDtoReq> persons;
    private Long city;
    private AccidentStatus status;
    private List<DirectCauseResp> causes;
    private List<ImageVueFormat> crashImages;
    private ImageVueFormat drawing;

}
