package com.ditros.mcd.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor @AllArgsConstructor
public class CareParamReq {
    private String poids;
    private String temperature;
    private String pouls;
    private String tension;
    private List<String> params;

}
