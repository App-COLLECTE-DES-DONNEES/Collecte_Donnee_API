package com.ditros.mcd.service;

import com.ditros.mcd.dao.CareExaminationDao;
import com.ditros.mcd.dao.ExaminationDao;
import com.ditros.mcd.model.dto.ExaminationResp;
import com.ditros.mcd.model.dto.InsuranceDecisionReq;
import com.ditros.mcd.model.entity.CareExamination;
import com.ditros.mcd.model.entity.Examination;
import com.ditros.mcd.model.mappers.ExaminationMapper;
import com.ditros.mcd.enumeration.InsuranceVisa;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ExaminationService {

    private ExaminationDao examinationDao;
    private CareExaminationDao careExaminationDao;
    private ExaminationMapper examinationMapper;
    private CareService careService;

    public List<ExaminationResp> getExamByName(String name, String lang){
        if(lang==null) lang="fr";
        List<ExaminationResp> models = new ArrayList<>();
        if(name.equals(""))
            return models;
        List<Examination> examinations = examinationDao.getExaminationByName(name, lang);

        examinations.forEach(
                e -> models.add(examinationMapper.fromExamination(e))
        );
        return models;
    }

    public Page<ExaminationResp> getExams(int size, int page, String lang){
        if(lang==null) lang="fr";
        List<ExaminationResp> models = new ArrayList<>();

        Pageable pageable = PageRequest.of(page, size);

        Page<Examination> exams = examinationDao.findByActiveStatusTrueAndLangOrderByNameDesc(lang, pageable);

        exams.forEach(
                examination -> models.add(examinationMapper.fromExamination(examination))
        );
        return new PageImpl<ExaminationResp>(models, pageable, exams.getTotalElements());
    }

    public boolean decision(InsuranceDecisionReq insuranceDecisionReq){
        insuranceDecisionReq.getItems().forEach(i ->{
            Optional<CareExamination> optionalCareExamination = careExaminationDao.findById(i);
            CareExamination careExamination = optionalCareExamination.orElseThrow(() -> new RuntimeException("Please provided a correct examination id! "));
            if(insuranceDecisionReq.isApprouved())
                careExamination.setInsuranceVisa(InsuranceVisa.ACCEPTED);
            else
                careExamination.setInsuranceVisa(InsuranceVisa.REJECTED);
            careService.checkInsuranceVisa(careExamination.getCare());
            careExaminationDao.save(careExamination);
        } );

        return true;
    }
}
