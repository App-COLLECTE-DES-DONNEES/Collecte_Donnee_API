package com.ditros.mcd.service;

import com.ditros.mcd.dao.AlcoholTestStatusDao;
import com.ditros.mcd.model.dto.AlcoholTestStatusResp;
import com.ditros.mcd.model.entity.AlcoholTestStatus;
import com.ditros.mcd.model.mappers.AlcoholTestStatusMapper;
import com.ditros.mcd.model.mappers.AlcoholTestTypeMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AlcoholTestStatusService {
    private AlcoholTestStatusMapper alcoholTestStatusMapper;
    private AlcoholTestStatusDao alcoholTestStatusDao;

    public List<AlcoholTestStatusResp> getAll(String lang) {
        if(lang==null) lang="fr";
        List<AlcoholTestStatus> testTypes = alcoholTestStatusDao.findByLang(lang);

        return testTypes.stream()
                .map(brand -> alcoholTestStatusMapper.fromAlcoholTestStatus(brand))
                .collect(Collectors.toList());
    }
}
