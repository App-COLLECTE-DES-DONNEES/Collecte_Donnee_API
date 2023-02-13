package com.ditros.mcd.service;

import com.ditros.mcd.dao.OccupantRestraintSystemDao;
import com.ditros.mcd.model.dto.OccupantRestraintSystemResp;
import com.ditros.mcd.model.entity.OccupantRestraintSystem;
import com.ditros.mcd.model.mappers.OccupantRestraintSystemMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service @AllArgsConstructor
public class OccupantRestraintSystemService {

    private OccupantRestraintSystemMapper restraintSystemMapper;

    private OccupantRestraintSystemDao restraintSystemDao;

    public List<OccupantRestraintSystemResp> getAll(String lang) {
        if(lang==null) lang="fr";
        List<OccupantRestraintSystem> restraintSystems = restraintSystemDao.findByLang(lang);

        return restraintSystems.stream()
                .map(restraintSystem -> restraintSystemMapper.fromOccupantRestraintSystem(restraintSystem))
                .collect(Collectors.toList());
    }
}
