package com.ditros.mcd.model.dto;

import com.ditros.mcd.enumeration.AccidentStatus;
import com.ditros.mcd.enumeration.OrganizationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class AccidentListResp {
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
}
