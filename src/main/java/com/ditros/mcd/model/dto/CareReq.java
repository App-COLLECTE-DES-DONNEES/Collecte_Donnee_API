package com.ditros.mcd.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor
public class CareReq {
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
    private List<ContactReq> contacts;
    private String description;
    private String plate;
}
