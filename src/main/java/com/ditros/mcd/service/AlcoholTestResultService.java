package com.ditros.mcd.service;

import com.ditros.mcd.dao.AlcoholTestResultDao;
import com.ditros.mcd.model.dto.AlcoholTestResultResp;
import com.ditros.mcd.model.entity.AlcoholTestResult;
import com.ditros.mcd.model.entity.PersonAlcoholConsumption;
import com.ditros.mcd.model.mappers.AlcoholTestResultMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
@Service
public class AlcoholTestResultService {
    private AlcoholTestResultMapper alcoholTestResultMapper;
    private AlcoholTestResultDao alcoholTestResultDao;

    public List<AlcoholTestResultResp> getAll(String lang) {
        if(lang==null) lang="fr";
        List<AlcoholTestResult> testResults = alcoholTestResultDao.findByLang(lang);

        return testResults.stream()
                .map(result -> alcoholTestResultMapper.fromAlcoholResult(result))
                .collect(Collectors.toList());
    }
}
