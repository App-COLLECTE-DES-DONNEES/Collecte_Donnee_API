package com.ditros.mcd.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseEntityResp {
    private int status;
    private boolean success;
    private String message;
    private InsuranceValidityResp data;
}
