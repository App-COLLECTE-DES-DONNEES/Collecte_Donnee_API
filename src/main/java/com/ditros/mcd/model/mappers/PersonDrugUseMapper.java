package com.ditros.mcd.model.mappers;

import com.ditros.mcd.model.dto.PersonDrugUseResp;
import com.ditros.mcd.model.dto.PersonRoadTypeResp;
import com.ditros.mcd.model.entity.PersonDrugUse;
import com.ditros.mcd.model.entity.PersonRoadType;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class PersonDrugUseMapper {
    public PersonDrugUseResp fromPersonDrugUse(PersonDrugUse personDrugUse) {
        if(personDrugUse==null) return null;
        PersonDrugUseResp personDrugUseResp = new PersonDrugUseResp();
        BeanUtils.copyProperties(personDrugUse, personDrugUseResp);

        return personDrugUseResp;
    }
}
