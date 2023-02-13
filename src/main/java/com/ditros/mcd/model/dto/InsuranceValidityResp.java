package com.ditros.mcd.model.dto;

import lombok.*;

@Data
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class InsuranceValidityResp {
    private String insurranceName;
    private boolean valid;
}
