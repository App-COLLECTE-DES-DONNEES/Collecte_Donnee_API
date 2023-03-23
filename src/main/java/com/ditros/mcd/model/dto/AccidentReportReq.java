package com.ditros.mcd.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class AccidentReportReq {
    private Long reportId;
    private Long idaccident;
    private String nous;
    private String assiste;
    private String constate;
    private String circulation;
    private String patrouille;

}
