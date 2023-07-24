package com.ditros.mcd.service;

import com.ditros.mcd.dao.RoadSlopSectionDao;
import com.ditros.mcd.model.dto.RoadSlopSectionResp;
import com.ditros.mcd.model.entity.RoadSlopSection;
import com.ditros.mcd.model.mappers.RoadSlopSectionMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service @AllArgsConstructor
public class RoadSlopSectionService {

    private RoadSlopSectionMapper slopSectionMapper;

    private RoadSlopSectionDao slopSectionDao;

    public List<RoadSlopSectionResp> getAll(String lang) {
        if(lang==null) lang="fr";
        List<RoadSlopSection> roadSlopSections = slopSectionDao.findByLang(lang);

        return roadSlopSections.stream()
                .map(slopSection -> slopSectionMapper.fromRoadSlopSection(slopSection))
                .collect(Collectors.toList());
    }
}
