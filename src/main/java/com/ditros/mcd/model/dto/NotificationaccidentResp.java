package com.ditros.mcd.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class NotificationaccidentResp {
    
private String Date;
private String hours;
private String city;
private Statut statut;
private String imageString;
private String description;


public enum Statut {
        ALLOWED,
        DISABLED
    
    }
}
