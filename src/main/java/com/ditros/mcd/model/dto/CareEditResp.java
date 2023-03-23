package com.ditros.mcd.model.dto;

import com.ditros.mcd.enumeration.CareStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CareEditResp {
    private Long id;
    private String identity;
    private Long personGender;
    private Long personTraumaSeverity;
    private String place;
    private LocalDateTime crashDate;
    private String description;
    private String birthDate;
    private CareStatus status;
}
