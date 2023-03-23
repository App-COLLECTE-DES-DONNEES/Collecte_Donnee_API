package com.ditros.mcd.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class CareFolderItemReq {
    private Long care;
    private Long item;
    private String date;
}
