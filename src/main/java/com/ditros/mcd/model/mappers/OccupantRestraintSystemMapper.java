package com.ditros.mcd.model.mappers;

import com.ditros.mcd.model.dto.OccupantRestraintSystemResp;
import com.ditros.mcd.model.entity.OccupantRestraintSystem;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class OccupantRestraintSystemMapper {

    public OccupantRestraintSystemResp fromOccupantRestraintSystem(OccupantRestraintSystem restraintSystem) {
        if(restraintSystem==null) return null;
        OccupantRestraintSystemResp systemResp = new OccupantRestraintSystemResp();
        BeanUtils.copyProperties(restraintSystem, systemResp);

        return systemResp;
    }
}
