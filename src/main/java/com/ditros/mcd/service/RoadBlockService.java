package com.ditros.mcd.service;

import com.ditros.mcd.dao.RoadBlockDao;
import com.ditros.mcd.model.dto.RoadBlockResp;
import com.ditros.mcd.model.entity.RoadBlock;
import com.ditros.mcd.model.mappers.RoadBlockMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service @AllArgsConstructor
public class RoadBlockService {

    private RoadBlockMapper blockMapper;

    private RoadBlockDao blockDao;

    public List<RoadBlockResp> getAll(String lang) {
        if(lang==null) lang="fr";
        List<RoadBlock> roadBlocks = blockDao.findByLang(lang);

        return roadBlocks.stream()
                .map(roadBlock -> blockMapper.blockResp(roadBlock))
                .collect(Collectors.toList());
    }
}
