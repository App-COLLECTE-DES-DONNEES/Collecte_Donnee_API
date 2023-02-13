package com.ditros.mcd.model.dto;

import com.ditros.mcd.enumeration.InsuranceVisa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreatmentResp {
    private Long id;
    private String name;
    private double price;
    private int number;
    private InsuranceVisa insuranceVisa;
    private String date;
}
