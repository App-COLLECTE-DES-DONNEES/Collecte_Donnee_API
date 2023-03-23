package com.ditros.mcd.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsuranceDecisionReq {
    private List<Long> items;
    private boolean approuved;
}
