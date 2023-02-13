package com.ditros.mcd.model.mappers;

import com.ditros.mcd.model.dto.ExaminationResp;
import com.ditros.mcd.model.entity.CareExamination;
import com.ditros.mcd.model.entity.Examination;
import com.ditros.mcd.util.DateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ExaminationMapper {
    public ExaminationResp fromCareExamination(CareExamination examination) {
        ExaminationResp examinationResp = new ExaminationResp();
        examinationResp.setId(examination.getId());
        examinationResp.setCare(examination.getCare().getId());
        examinationResp.setPrice(examination.getExamination().getPrice());
        examinationResp.setInsuranceVisa(examination.getInsuranceVisa());
        examinationResp.setName(examination.getExamination().getName());
        examinationResp.setDate(DateUtil.textFromDate("dd/MM/yyyy HH:mm", examination.getDate()));

        return examinationResp;
    }
    public ExaminationResp fromExamination(Examination examination) {
        ExaminationResp examinationResp = new ExaminationResp();
        BeanUtils.copyProperties(examination, examinationResp);
        return examinationResp;
    }

}
