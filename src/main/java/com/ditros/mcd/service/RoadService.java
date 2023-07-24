package com.ditros.mcd.service;

import com.ditros.mcd.dao.RoadDao;
import com.ditros.mcd.model.dto.RoadResp;
import com.ditros.mcd.model.entity.Road;
import com.ditros.mcd.model.mappers.RoadMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service @AllArgsConstructor
public class RoadService {

    private RoadDao roadDao;

    private RoadMapper roadMapper;

    public List<RoadResp> getAll(String lang) {
        if(lang==null) lang="fr";
        List<Road> roads = roadDao.findByLang(lang);

        return roads.stream()
                .map(road -> roadMapper.fromRoad(road))
                .collect(Collectors.toList());
    }


}
