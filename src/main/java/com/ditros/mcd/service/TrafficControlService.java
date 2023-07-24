package com.ditros.mcd.service;

import com.ditros.mcd.dao.RoadTrafficControlDao;
import com.ditros.mcd.model.dto.TrafficControlResp;
import com.ditros.mcd.model.entity.RoadTrafficControl;
import com.ditros.mcd.model.mappers.TrafficControlMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service @AllArgsConstructor
public class TrafficControlService {

    private TrafficControlMapper controlMapper;
    private RoadTrafficControlDao controlDao;

    public List<TrafficControlResp> getAll(String lang) {

        if(lang==null) lang="fr";
        List<RoadTrafficControl> trafficControls = controlDao.findByLang(lang);

        return trafficControls.stream()
                .map(trafficControl -> controlMapper.fromTrafficControlResp(trafficControl))
                .collect(Collectors.toList());
    }

}
