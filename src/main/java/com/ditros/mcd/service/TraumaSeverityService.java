package com.ditros.mcd.service;

import com.ditros.mcd.dao.PersonTraumaSeverityDao;
import com.ditros.mcd.model.dto.TraumaSeverityResp;
import com.ditros.mcd.model.entity.PersonTraumaSeverity;
import com.ditros.mcd.model.mappers.TraumaSeverityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service @AllArgsConstructor
public class TraumaSeverityService {
    private TraumaSeverityMapper severityMapper;
    private PersonTraumaSeverityDao severityDao;

    public List<TraumaSeverityResp> getAll(String lang) {
        if(lang==null) lang="fr";
        List<PersonTraumaSeverity> severities = severityDao.findByLang(lang);

        return severities.stream()
                .map(traumaSeverity -> severityMapper.fromTraumaSeverity(traumaSeverity))
                .collect(Collectors.toList());
    }
}
