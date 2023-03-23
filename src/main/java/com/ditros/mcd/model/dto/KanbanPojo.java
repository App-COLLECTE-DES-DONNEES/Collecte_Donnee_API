package com.ditros.mcd.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor @AllArgsConstructor
public class KanbanPojo {
    private String legend;
    private List<Pojo> items;
    private int size;
}
