package com.ditros.mcd.model.mappers;

import com.ditros.mcd.model.dto.RoadBlockResp;
import com.ditros.mcd.model.entity.RoadBlock;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class RoadBlockMapper {

    public RoadBlockResp blockResp(RoadBlock block) {
        if(block==null) return null;
        RoadBlockResp blockResp = new RoadBlockResp();
        BeanUtils.copyProperties(block, blockResp);

        return blockResp;
    }
}
