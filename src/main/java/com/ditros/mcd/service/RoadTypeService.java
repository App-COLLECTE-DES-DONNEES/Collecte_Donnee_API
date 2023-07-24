package com.ditros.mcd.service;

import com.ditros.mcd.dao.RoadTypeDao;
import com.ditros.mcd.model.dto.RoadTypeResp;
import com.ditros.mcd.model.entity.RoadType;
import com.ditros.mcd.model.mappers.RoadTypeMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service @AllArgsConstructor
public class RoadTypeService {

    private RoadTypeMapper roadTypeMapper;

    private RoadTypeDao roadTypeDao;

    public List<RoadTypeResp> getAll(String lang) {

        List<RoadType> roadTypes = roadTypeDao.findByLang(lang);

        return roadTypes.stream()
                .map(roadType -> roadTypeMapper.fromRoadType(roadType))
                .collect(Collectors.toList());
    }
}
