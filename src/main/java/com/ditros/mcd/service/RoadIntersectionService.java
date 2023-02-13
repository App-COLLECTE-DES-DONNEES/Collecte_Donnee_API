package com.ditros.mcd.service;

import com.ditros.mcd.dao.RoadIntersectionDao;
import com.ditros.mcd.model.dto.RoadIntersectionResp;
import com.ditros.mcd.model.entity.RoadIntersection;
import com.ditros.mcd.model.mappers.RoadIntersectionMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service @AllArgsConstructor
public class RoadIntersectionService {

    private RoadIntersectionDao intersectionDao;

    private RoadIntersectionMapper intersectionMapper;

    public List<RoadIntersectionResp> getAll(String lang) {
        if(lang==null) lang="fr";
        List<RoadIntersection> roadIntersections = intersectionDao.findAll();

        return roadIntersections.stream()
                .map(intersection -> intersectionMapper.fromRoadIntersection(intersection))
                .collect(Collectors.toList());
    }
}
