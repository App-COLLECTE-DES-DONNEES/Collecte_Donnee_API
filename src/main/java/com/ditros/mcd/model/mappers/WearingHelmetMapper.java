package com.ditros.mcd.model.mappers;

import com.ditros.mcd.model.dto.WearingHelmetResp;
import com.ditros.mcd.model.entity.WearingHelmet;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class WearingHelmetMapper {

    public WearingHelmetResp fromWearingHelmet(WearingHelmet wearingHelmet) {
        if(wearingHelmet==null) return null;
        WearingHelmetResp helmetResp = new WearingHelmetResp();
        BeanUtils.copyProperties(wearingHelmet, helmetResp);

        return helmetResp;
    }
}
