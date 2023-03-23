package com.ditros.mcd.service;

import com.ditros.mcd.dao.CareTreatmentDao;
import com.ditros.mcd.dao.TreatmentDao;
import com.ditros.mcd.model.dto.InsuranceDecisionReq;
import com.ditros.mcd.model.dto.TreatmentResp;
import com.ditros.mcd.model.entity.CareTreatment;
import com.ditros.mcd.model.entity.Treatment;
import com.ditros.mcd.model.mappers.TreatmentMapper;
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
public class TreatmentService {
    private TreatmentDao treatmentDao;
    private TreatmentMapper treatmentMapper;
    private CareTreatmentDao careTreatmentDao;
    private CareService careService;

    public List<TreatmentResp> getTreatmentByName(String name, String lang){
        if(lang==null) lang="fr";
        List<TreatmentResp> models = new ArrayList<>();
        if(name.equals(""))
            return models;
        List<Treatment> treatments = treatmentDao.getTreatmentByName(name, lang);

        treatments.forEach(
                t -> models.add(treatmentMapper.fromTreatment(t))
        );
        return models;
    }

    public Page<TreatmentResp> getTreats(int size, int page,String lang ){
        List<TreatmentResp> models = new ArrayList<>();

        Pageable pageable = PageRequest.of(page, size);
        Page<Treatment> treatments = treatmentDao.findByActiveStatusTrueAndLangOrderByNameDesc(lang, pageable);

        treatments.forEach(
                t -> models.add(treatmentMapper.fromTreatment(t))
        );

        return new PageImpl<TreatmentResp>(models, pageable, treatments.getTotalElements());

    }

    public boolean decision(InsuranceDecisionReq insuranceDecisionReq){
        insuranceDecisionReq.getItems().forEach(i -> {
            Optional<CareTreatment> optionalCareTreatment = careTreatmentDao.findById(i);
            CareTreatment careTreatment = optionalCareTreatment.orElseThrow(() -> new RuntimeException("Please provided a correct treatment id! "));

            if(insuranceDecisionReq.isApprouved())
                careTreatment.setInsuranceVisa(InsuranceVisa.ACCEPTED);
            else
                careTreatment.setInsuranceVisa(InsuranceVisa.REJECTED);
            careService.checkInsuranceVisa(careTreatment.getCare());
            careTreatmentDao.save(careTreatment);
        });


        return true;

    }

}
