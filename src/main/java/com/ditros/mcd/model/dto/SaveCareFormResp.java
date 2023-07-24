package com.ditros.mcd.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor
public class SaveCareFormResp {
    private List<TraumaSeverityResp> traumaSeverityRespList;
    private List<GenderResp> genderRespList;
}
