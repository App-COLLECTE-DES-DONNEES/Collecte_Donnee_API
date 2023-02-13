package com.ditros.mcd.service;

import com.ditros.mcd.dao.AlcoholTestTypeDao;
import com.ditros.mcd.dao.VehicleBrandDao;
import com.ditros.mcd.model.dto.AlcoholTestTypeResp;
import com.ditros.mcd.model.dto.BrandResp;
import com.ditros.mcd.model.entity.AlcoholTestType;
import com.ditros.mcd.model.entity.VehicleBrand;
import com.ditros.mcd.model.mappers.AlcoholTestTypeMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AlcoholTestTypeService {
    private AlcoholTestTypeMapper alcoholTestTypeMapper;
    private AlcoholTestTypeDao alcoholTestTypeDao;

    public List<AlcoholTestTypeResp> getAll(String lang) {
        if(lang==null) lang="fr";
        List<AlcoholTestType> testTypes = alcoholTestTypeDao.findByLang(lang);

        return testTypes.stream()
                .map(brand -> alcoholTestTypeMapper.fromAlcoholTestType(brand))
                .collect(Collectors.toList());
    }
}
