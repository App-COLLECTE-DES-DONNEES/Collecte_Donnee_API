package com.ditros.mcd.service;

import com.ditros.mcd.dao.AccidentSeverityDao;
import com.ditros.mcd.model.dto.AccidentSeverityResp;
import com.ditros.mcd.model.entity.AccidentSeverity;
import com.ditros.mcd.model.mappers.AccidentSeverityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AccidentSeverityService {

    private AccidentSeverityDao severityDao;
    private AccidentSeverityMapper severityMapper;

    public List<AccidentSeverityResp> getAll(String lang) {
        if(lang==null) lang="fr";
        List<AccidentSeverity> severities = severityDao.findByLang(lang);

        return severities.stream()
                .map(severity -> severityMapper.fromSeverity(severity))
                .collect(Collectors.toList());
    }
}