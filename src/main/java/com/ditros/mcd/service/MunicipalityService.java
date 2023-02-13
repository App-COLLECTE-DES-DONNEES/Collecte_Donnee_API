package com.ditros.mcd.service;

import com.ditros.mcd.dao.MunicipalityDao;
import com.ditros.mcd.model.dto.MunicipalityResp;
import com.ditros.mcd.model.entity.Municipality;
import com.ditros.mcd.model.mappers.MunicipalityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MunicipalityService {

    private MunicipalityMapper municipalityMapper;

    private MunicipalityDao municipalityDao;

    public List<MunicipalityResp> getAll() {
        List<Municipality> municipalities = municipalityDao.findAll();
        return municipalities.stream()
                .map(municipality -> municipalityMapper.fromMunicipality(municipality))
                .collect(Collectors.toList());
    }
}
