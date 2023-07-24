package com.ditros.mcd.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SafetyEquipmentReq {
    private Long wearingHelmet;
    private Long occupantRestraintSystem;
}
