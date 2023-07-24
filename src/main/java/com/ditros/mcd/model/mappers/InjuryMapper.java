package com.ditros.mcd.model.mappers;

import com.ditros.mcd.model.dto.ExaminationResp;
import com.ditros.mcd.model.dto.InjuryResp;
import com.ditros.mcd.model.entity.CareInjury;
import com.ditros.mcd.model.entity.Examination;
import com.ditros.mcd.model.entity.Injury;
import com.ditros.mcd.util.DateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class InjuryMapper {
    public InjuryResp fromInjury(CareInjury injury) {
        InjuryResp injuryResp = new InjuryResp();
        BeanUtils.copyProperties(injury, injuryResp);
        injuryResp.setName(injury.getInjury().getName());
        injuryResp.setCare(injury.getCare().getId());
        injuryResp.setDate(DateUtil.textFromDate("dd/MM/yyyy HH:mm", injury.getDate()));
        return injuryResp;
    }

    public InjuryResp fromInjury(Injury injury) {
        InjuryResp injuryResp = new InjuryResp();
        BeanUtils.copyProperties(injury, injuryResp);
        return injuryResp;
    }
}
