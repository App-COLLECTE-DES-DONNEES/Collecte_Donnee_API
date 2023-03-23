package com.ditros.mcd.model.mappers;

import com.ditros.mcd.model.dto.AlcoholTestResultResp;
import com.ditros.mcd.model.dto.AlcoholTestStatusResp;
import com.ditros.mcd.model.entity.AlcoholTestResult;
import com.ditros.mcd.model.entity.AlcoholTestStatus;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class AlcoholTestResultMapper {
    public AlcoholTestResultResp fromAlcoholResult(AlcoholTestResult alcoholTestResult) {
        if(alcoholTestResult==null) return null;
        AlcoholTestResultResp alcoholTestResultResp = new AlcoholTestResultResp();
        BeanUtils.copyProperties(alcoholTestResult, alcoholTestResultResp);

        return alcoholTestResultResp;
    }
}
