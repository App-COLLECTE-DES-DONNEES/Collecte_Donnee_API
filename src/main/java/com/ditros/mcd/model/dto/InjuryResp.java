package com.ditros.mcd.model.dto;

import com.ditros.mcd.enumeration.InsuranceVisa;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InjuryResp {
    private Long id;
    private String name;

    private Long care;
    private InsuranceVisa insuranceVisa;
    private String date;
}
