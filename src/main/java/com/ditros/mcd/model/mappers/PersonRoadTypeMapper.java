package com.ditros.mcd.model.mappers;

import com.ditros.mcd.model.dto.PersonRoadTypeResp;
import com.ditros.mcd.model.entity.PersonRoadType;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class PersonRoadTypeMapper {

    public PersonRoadTypeResp fromPersonRoadType(PersonRoadType roadType) {
        if(roadType==null) return null;
        PersonRoadTypeResp roadTypeResp = new PersonRoadTypeResp();
        BeanUtils.copyProperties(roadType, roadTypeResp);

        return roadTypeResp;
    }
}
