package com.ditros.mcd.service;

import com.ditros.mcd.dao.RoadCategoryDao;
import com.ditros.mcd.model.dto.RoadCategoryResp;
import com.ditros.mcd.model.entity.RoadCategory;
import com.ditros.mcd.model.mappers.RoadCategoryMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service @AllArgsConstructor
public class RoadCategoryService {

    private RoadCategoryDao categoryDao;

    private RoadCategoryMapper categoryMapper;

    public List<RoadCategoryResp> getAll(String lang) {
        if(lang==null) lang="fr";
        List<RoadCategory> roadCategories = categoryDao.findAll();

        return roadCategories.stream()
                .map(roadCategory -> categoryMapper.fromRoadCategory(roadCategory))
                .collect(Collectors.toList());
    }
}
