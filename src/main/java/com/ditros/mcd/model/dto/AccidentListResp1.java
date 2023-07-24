package com.ditros.mcd.model.dto;

import java.util.List;
import java.util.Map;

import com.ditros.mcd.enumeration.AccidentStatus;
import com.ditros.mcd.enumeration.OrganizationType;
import com.ditros.mcd.model.entity.Accident;
import com.ditros.mcd.model.entity.AccidentReport;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class AccidentListResp1 {
    private Long id;
    private String place;
    private String severity;
    private String crashDate;
    private int personInvolved;
    private int inCare;
    private String city;
    private String officerName;
    private AccidentStatus status;
    private String customerPlate;
    private String customerName;
    private String code;
    private Long accidentId;
    private double amount;
    private double amountAccepted;
    private String crashTime;
    private Long vehicleNumber;
    private String drawing;
    private OrganizationType organizationType; 
    private List<Map<String, String>> crashImageList;
    private AccidentReportResp report;
    
}



