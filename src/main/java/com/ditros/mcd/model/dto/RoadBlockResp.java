package com.ditros.mcd.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
@AllArgsConstructor
public class RoadBlockResp {
    private Long id;
    private long code;
    private String value;
}
