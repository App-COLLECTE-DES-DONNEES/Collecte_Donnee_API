package com.ditros.mcd.model.mappers;


import com.ditros.mcd.model.dto.AccidentTypeResp;
import com.ditros.mcd.model.entity.AccidentType;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class AccidentTypeMapper {

    public AccidentTypeResp fromAccidentType(AccidentType accidentType) {

        if(accidentType==null) return null;
        AccidentTypeResp accidentTypeResp = new AccidentTypeResp();
        BeanUtils.copyProperties(accidentType, accidentTypeResp);

        return accidentTypeResp;
    }
}
