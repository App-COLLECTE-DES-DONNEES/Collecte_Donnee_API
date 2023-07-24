package com.ditros.mcd.model.mappers;

import com.ditros.mcd.model.dto.VirageResp;
import com.ditros.mcd.model.entity.Virage;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class VirageMapper {

    public VirageResp fromVirage(Virage virage){
        if(virage==null) return null;
        VirageResp virageResp = new VirageResp();
        BeanUtils.copyProperties(virage, virageResp);

        return virageResp;
    }
}
