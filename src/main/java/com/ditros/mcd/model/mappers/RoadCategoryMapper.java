package com.ditros.mcd.model.mappers;

import com.ditros.mcd.model.dto.RoadCategoryResp;
import com.ditros.mcd.model.entity.RoadCategory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class RoadCategoryMapper {

    public RoadCategoryResp fromRoadCategory(RoadCategory roadCategory) {
        if(roadCategory==null) return null;
        RoadCategoryResp categoryResp = new RoadCategoryResp();
        BeanUtils.copyProperties(roadCategory, categoryResp);

        return categoryResp;
    }
}
