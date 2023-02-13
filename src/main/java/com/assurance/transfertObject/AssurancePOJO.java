package com.assurance.transfertObject;

import com.assurance.entities.Organisation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssurancePOJO {
    private UUID id;
    private String police;
    private String name;
    private String souscripteur;
    private String pf;
    private double primeAssurance;
    private Organisation organisation;
    private String vehicule;
    private String date_debut;
    private String date_fin;
    private CustomerReq customer;
    private String cat;
    private String chassis;
    private String marque;
    private String description;
}
