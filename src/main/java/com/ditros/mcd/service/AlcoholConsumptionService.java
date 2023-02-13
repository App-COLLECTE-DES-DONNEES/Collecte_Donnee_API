package com.ditros.mcd.service;

import com.ditros.mcd.dao.PersonAlcoholConsumptionDao;
import com.ditros.mcd.model.dto.AlcoholConsumptionResp;
import com.ditros.mcd.model.entity.PersonAction;
import com.ditros.mcd.model.entity.PersonAlcoholConsumption;
import com.ditros.mcd.model.mappers.AlcoholConsumptionMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AlcoholConsumptionService {
    private AlcoholConsumptionMapper alcoholConsumptionMapper;
    private PersonAlcoholConsumptionDao personAlcoholConsumptionDao;

    public List<AlcoholConsumptionResp> getAll(String lang) {
        if(lang==null) lang="fr";
        List<PersonAlcoholConsumption> alcoholConsumptions = personAlcoholConsumptionDao.findByLang(lang);

        return alcoholConsumptions.stream()
                .map(brand -> alcoholConsumptionMapper.fromAlcoholConsumption(brand))
                .collect(Collectors.toList());
    }
}
