package com.ditros.mcd.service;

import com.ditros.mcd.dao.AccidentClimaticConditionDao;
import com.ditros.mcd.dao.CityDao;
import com.ditros.mcd.model.dto.CityResp;
import com.ditros.mcd.model.dto.ClimaticConditionResp;
import com.ditros.mcd.model.entity.AccidentClimaticCondition;
import com.ditros.mcd.model.entity.City;
import com.ditros.mcd.model.mappers.CityMapper;
import com.ditros.mcd.model.mappers.ClimaticConditionMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CityService {
    private CityMapper cityMapper;
    private CityDao cityDao;

    public List<CityResp> getAll() {
        List<City> cities = cityDao.findAll();

        return cities.stream()
                .map(city -> cityMapper.fromCity(city))
                .collect(Collectors.toList());
    }
}
