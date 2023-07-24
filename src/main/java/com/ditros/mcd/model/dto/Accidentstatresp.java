package com.ditros.mcd.model.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;

import org.hibernate.mapping.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Accidentstatresp {
    String Month;
    Integer accidentcount;


}
