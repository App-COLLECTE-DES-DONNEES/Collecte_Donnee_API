package com.ditros.mcd.service;

import com.ditros.mcd.dao.RoadStateDao;
import com.ditros.mcd.model.dto.RoadStateResp;
import com.ditros.mcd.model.entity.RoadState;
import com.ditros.mcd.model.mappers.RoadStateMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service @AllArgsConstructor
public class RoadStateService {

    private RoadStateMapper roadStateMapper;

    private RoadStateDao roadStateDao;

    public List<RoadStateResp> getAll(String lang) {
        if(lang==null) lang="fr";
        List<RoadState> roadStates = roadStateDao.findByLang(lang);

        return roadStates.stream()
                .map(roadState -> roadStateMapper.fromRoadState(roadState))
                .collect(Collectors.toList());
    }
}
