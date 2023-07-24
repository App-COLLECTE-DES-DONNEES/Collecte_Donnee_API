package com.ditros.mcd.model.dto;

import com.ditros.mcd.enumeration.InsuranceVisa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExaminationResp {
    private Long id;
    private String name;
    private double price;
    private int number;
    private Long care;
    private InsuranceVisa insuranceVisa;
    private String date;
}
