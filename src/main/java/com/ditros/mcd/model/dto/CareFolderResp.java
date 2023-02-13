package com.ditros.mcd.model.dto;

import com.ditros.mcd.enumeration.CareStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CareFolderResp {
    private Long id;
    private String cni;
    private String nom;
    private String prenom;
    private String telephone;
    private String dateNaiss;
    private String passport;
    private String permis;
    private Long gender;
    private AccParamReq accparams;
    private CareParamReq parametre;
    private String code;
    private CareStatus status;
    private String crashDate;
    private List<ExaminationResp> exams;
    private List<TreatmentResp> treatments;
    private List<InjuryResp> injuries;
    private List<ContactReq> contacts;
    private double amountAccepted;
    private String insurance;
}
