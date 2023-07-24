package com.ditros.mcd.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class CareFolderItemReq {
    private Long care;
    private Long item;
    private String date;
    private Boolean requesttype=false;
    private Long idtreatment=new Long (0);
}
