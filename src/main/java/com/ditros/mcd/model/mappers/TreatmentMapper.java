package com.ditros.mcd.model.mappers;

import com.ditros.mcd.model.dto.ExaminationResp;
import com.ditros.mcd.model.dto.TreatmentResp;
import com.ditros.mcd.model.entity.CareTreatment;
import com.ditros.mcd.model.entity.Examination;
import com.ditros.mcd.model.entity.Treatment;
import com.ditros.mcd.util.DateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class TreatmentMapper {
    public TreatmentResp fromTreatment(CareTreatment treatment) {
        TreatmentResp treatmentResp = new TreatmentResp();
        BeanUtils.copyProperties(treatment, treatmentResp);
        treatmentResp.setName(treatment.getTreatment().getName());
        treatmentResp.setPrice(treatment.getTreatment().getPrice());
        treatmentResp.setInsuranceVisa(treatment.getInsuranceVisa());
        treatmentResp.setDate(DateUtil.textFromDate("dd/MM/yyyy HH:mm", treatment.getDate()));
        return treatmentResp;
    }

    public TreatmentResp fromTreatment(Treatment treatment) {
        TreatmentResp treatmentResp = new TreatmentResp();
        BeanUtils.copyProperties(treatment, treatmentResp);
        return treatmentResp;
    }
}
