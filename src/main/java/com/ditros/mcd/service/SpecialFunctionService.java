package com.ditros.mcd.service;

import com.ditros.mcd.dao.VehicleSpecialFunctionDao;
import com.ditros.mcd.model.dto.SpecialFunctionResp;
import com.ditros.mcd.model.entity.VehicleSpecialFunction;
import com.ditros.mcd.model.mappers.SpecialFunctionMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service @AllArgsConstructor
public class SpecialFunctionService {

    private SpecialFunctionMapper functionMapper;
    private VehicleSpecialFunctionDao specialFunctionDao;

    public List<SpecialFunctionResp> getAll(String lang) {
        if(lang==null) lang="fr";
        List<VehicleSpecialFunction> specialFunctions = specialFunctionDao.findByLang(lang);

        return specialFunctions.stream()
                .map(specialFunction -> functionMapper.fromSpecialFunction(specialFunction))
                .collect(Collectors.toList());
    }
}
