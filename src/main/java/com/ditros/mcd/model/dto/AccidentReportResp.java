package com.ditros.mcd.model.dto;

import com.ditros.mcd.model.entity.Accident;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class AccidentReportResp {
    private Long reportId;
    private Long idaccident;
    private String nous;
    private String assiste;
    private String constate;
    private String circulation;
    private String patrouille;

 public AccidentReportResp fillaccidentReportResp(Accident accident){
        this.reportId=accident.getAccidentReport().getId();
        this.idaccident=accident.getId();
        this.nous=accident.getAccidentReport().getAuthors();
        this.assiste=accident.getAccidentReport().getAssist();
        this.constate=accident.getAccidentReport().getSaw();
        this.circulation=accident.getAccidentReport().getCirculation();
        this.patrouille=accident.getAccidentReport().getPatrol(); 
   return this;
    }
}


