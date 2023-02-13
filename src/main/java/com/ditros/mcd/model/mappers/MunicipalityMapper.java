package com.ditros.mcd.model.mappers;

import com.ditros.mcd.model.dto.MunicipalityResp;
import com.ditros.mcd.model.entity.Municipality;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class MunicipalityMapper {

    public MunicipalityResp fromMunicipality(Municipality municipality) {
        MunicipalityResp municipalityResp = new MunicipalityResp();
        BeanUtils.copyProperties(municipality, municipalityResp);

        return municipalityResp;
    }
}
