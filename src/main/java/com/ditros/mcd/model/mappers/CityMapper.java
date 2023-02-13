package com.ditros.mcd.model.mappers;

import com.ditros.mcd.model.dto.CityResp;
import com.ditros.mcd.model.dto.ClimaticConditionResp;
import com.ditros.mcd.model.entity.AccidentClimaticCondition;
import com.ditros.mcd.model.entity.City;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class CityMapper {
    public CityResp fromCity(City city) {
        CityResp cityResp = new CityResp();
        BeanUtils.copyProperties(city, cityResp);

        return cityResp;
    }
}
