package com.ditros.mcd.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor
public class KanbanCareSimpleResp {
    private List<Pojo> assurances;
    private String legend;
    private int size;
}
