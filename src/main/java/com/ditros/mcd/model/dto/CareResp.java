package com.ditros.mcd.model.dto;


import com.ditros.mcd.enumeration.CareStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CareResp {
    private Long id;
    private String cni;
    private String nom;
    private String prenom;
    private String telephone;
    private String dateNaiss;
    private String passport;
    private String permis;
    private String gender;
    private AccParamReq accparams;
    private CareParamReq parametre;
    private String crashDate;
    private CareStatus status;
    private String code;
    private double amount;
    private String hopital;
    private double amountAccepted;
    private String trauma;
    private String insurance;
    private String accidentRole;
    private String description;
}
