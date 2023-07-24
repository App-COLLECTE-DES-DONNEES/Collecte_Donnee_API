package com.ditros.mcd.model.dto;

import com.ditros.mcd.model.entity.Accident;
import com.ditros.mcd.model.entity.Country;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@NoArgsConstructor @AllArgsConstructor
public class CityResp {
    private Long id;
    private String name;
}
