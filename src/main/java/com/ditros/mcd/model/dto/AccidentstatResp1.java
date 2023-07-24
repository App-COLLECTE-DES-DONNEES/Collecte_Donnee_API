package com.ditros.mcd.model.dto;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccidentstatResp1 {
    
    Integer totalnumberaccident;
    Map<String, Integer> stats ;


}
